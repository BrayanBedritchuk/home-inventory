package br.com.sailboat.homeinventory.view.product.insert

import android.os.Bundle
import br.com.sailboat.canoe.base.BasePresenter
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.canoe.helper.LogHelper
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.domain.ProductLoader
import br.com.sailboat.homeinventory.domain.ProductValidator
import br.com.sailboat.homeinventory.presentation.helper.Extras


class ProductInsertPresenter(view: View, val repositoryFactory: RepositoryFactory) : BasePresenter<ProductInsertPresenter.View>(view) {

    val viewModel = ProductInsertViewModel()

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

    fun onClickMenuSave() {
        try {
            closeKeyboard()
            extractInfoFromViews()
            val product = buildProductFromViewModel()
            ProductValidator.validate(context, product)
            save(product)

        } catch (e: RequiredFieldNotFilledException) {
            showMessage(e.message)

        } catch (e: Exception) {
            printLogAndShowDialog(e)
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
                val product = ProductLoader(
                    repositoryFactory.productRepository
                )
                    .loadProduct(viewModel.productId)
                viewModel.name = product.name
                viewModel.quantity = product.quantity
            }

            override fun onSuccess() {
                getView().setActivityToHideKeyboard()
                updateEditTexts()
                updateContentViews()
            }

            override fun onFail(e: Exception) {
                LogHelper.logException(e)
                view.showToast(getString(R.string.msg_exception_start_editing))
                closeActivityWithResultCanceled()
            }

        })
    }

    private fun save(product: Product) {
        executeAsyncWithProgress(object : AsyncHelper.Callback {

            override fun doInBackground() {
//                ProductSaver(context).save(product)
            }

            override fun onSuccess() {
                closeActivityWithResultOk()
            }

            override fun onFail(e: java.lang.Exception?) {
                LogHelper.logException(e)
                showMessage(getString(R.string.msg_exception_saving))
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

    private fun updateEditTexts() {
        view.setName(viewModel.name)
        view.setQuantity(viewModel.quantity.toString())
    }


    interface View : BasePresenter.View {
        fun getName(): String
        fun getQuantity(): String
        fun setName(name: String)
        fun setQuantity(quantity: String)
    }

}


