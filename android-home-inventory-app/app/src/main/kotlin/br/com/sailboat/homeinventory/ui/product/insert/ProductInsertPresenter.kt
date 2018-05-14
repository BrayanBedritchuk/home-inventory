package br.com.sailboat.homeinventory.ui.product.insert

import android.os.Bundle
import br.com.sailboat.canoe.base.BasePresenter
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.canoe.helper.LogHelper
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.exception.InvalidFieldException
import br.com.sailboat.homeinventory.core.interactor.product.GetProduct
import br.com.sailboat.homeinventory.core.interactor.product.ProductValidator
import br.com.sailboat.homeinventory.core.interactor.product.SaveProduct
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.helper.Extras


class ProductInsertPresenter(
    view: View,
    val repositoryFactory: RepositoryFactory?
) : BasePresenter<ProductInsertPresenter.View>(view) {

    val viewModel = ProductInsertViewModel()

    private val TAG = "ProductInsertPresenter"

    override fun extractExtrasFromArguments(arguments: Bundle?) {
        arguments?.let {
            val cardId = Extras.getProductId(it)
            viewModel.productId = cardId
        }
    }

    override fun onResumeFirstSession() {
        if (hasProductToEdit()) {
            startEditingProduct()
        } else {
            updateContentViews()
        }
    }

    override fun onResumeAfterRestart() {
        updateContentViews()
    }

    fun onClickSave() {
        try {
            closeKeyboard()
            extractInfoFromViews()
            val product = buildProductFromViewModel()
            save(product)
        } catch (e: Exception) {
            showMessage(getString(R.string.msg_error))
        }

    }

    private fun handleInvalidProducFields(e: InvalidFieldException) {
        e.rules?.forEach {
            when (it) {
                ProductValidator.InvalidProductFields.NAME_NOT_FILLED -> {
                    view.showMessageNameNotFilled();
                }
                ProductValidator.InvalidProductFields.QUANTITY_NEGATIVE -> {
                    view.showMessageQuantityNegative();
                }
            }
        }
    }

    private fun extractInfoFromViews() {
        viewModel.name = view.getName()
        viewModel.quantity = getIntFromString(view.getQuantity())
    }

    private fun buildProductFromViewModel(): Product {
        val product = Product()

        product.id = viewModel.productId
        product.name = viewModel.name
        product.quantity = viewModel.quantity

        return product
    }

    private fun startEditingProduct() {
        executeAsyncWithProgress(object : AsyncHelper.Callback {

            @Throws(Exception::class)
            override fun doInBackground() {
                loadProduct()
            }

            override fun onSuccess() {
                getView().setActivityToHideKeyboard()
                updateInputTexts()
                updateContentViews()
            }

            override fun onFail(e: Exception) {
                LogHelper.logException(e)
                view.showToast(getString(R.string.msg_exception_start_editing))
                closeActivityWithResultCanceled()
            }

            private fun loadProduct() {
                val product = GetProduct(
                    repositoryFactory?.productRepository,
                    viewModel.productId
                ).execute()

                viewModel.name = product.name
                viewModel.quantity = product.quantity
            }

        })
    }

    private fun save(product: Product) {
        showProgress()
        AsyncHelper.execute(object : AsyncHelper.Callback {

            override fun doInBackground() {
                SaveProduct(
                    product,
                    repositoryFactory?.productRepository
                ).execute()
            }

            override fun onSuccess() {
                dismissProgress()
                closeActivityWithResultOk()
            }

            override fun onFail(e: Exception) {
                dismissProgress()
                if (e is InvalidFieldException) {
                    handleInvalidProducFields(e)
                } else {
                    showMessage(getString(R.string.msg_error))
                }
            }
        })

    }

    private fun updateContentViews() {
        updateTitle()
    }

    private fun updateTitle() {
        if (hasProductToEdit()) {
            view.setTitle(getString(R.string.title_edit_product))
        } else {
            view.setTitle(getString(R.string.title_new_product))
        }
    }

    private fun hasProductToEdit() = viewModel.productId != EntityHelper.NO_ID

    private fun updateInputTexts() {
        view.setName(viewModel.name)
        view.setQuantity(viewModel.quantity.toString())
    }


    interface View : BasePresenter.View {
        fun getName(): String
        fun getQuantity(): String
        fun setName(name: String)
        fun setQuantity(quantity: String)
        fun showMessageNameNotFilled()
        fun showMessageQuantityNegative()
    }

}


