package br.com.sailboat.homeinventory.ui.product.insert

import android.content.Intent
import android.util.Log
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.core.interactor.product.ProductValidator
import br.com.sailboat.homeinventory.domain.entity.EntityHelper
import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.usecase.GetProduct
import br.com.sailboat.homeinventory.domain.usecase.SaveProduct
import br.com.sailboat.homeinventory.domain.usecase.ValidateProduct
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import br.com.sailboat.homeinventory.ui.helper.Extras
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class ProductInsertPresenter @Inject constructor(
    private val viewModel: ProductInsertViewModel,
    private val getProduct: GetProduct,
    private val saveProduct: SaveProduct,
    private val validateProduct: ValidateProduct
) : BasePresenter<ProductInsertPresenter.View>() {

    override fun extractArgs(intent: Intent?) {
        intent?.let {
            if (Extras.hasProductId(it)) {
                val cardId = Extras.getProductId(it)
                viewModel.productId = cardId
            }
        }
    }

    override fun create() {
        if (hasProductToEdit()) {
            startEditingProduct()
        } else {
            updateContentViews()
        }
    }

    override fun restart() {
        updateContentViews()
    }

    fun onClickSave() {
        try {
            view?.closeKeyboard()
            extractInfoFromViews()
            val product = buildProductFromViewModel()
            save(product)
        } catch (e: Exception) {
            view?.logError(e)
            view?.showErrorMessage(R.string.msg_error)
        }
    }

    private fun extractInfoFromViews() {
        viewModel.name = view?.getName() ?: ""
        viewModel.quantity = view?.getQuantity()?.toIntOrNull() ?: 0
    }

    private fun buildProductFromViewModel(): Product {
        return Product(viewModel.productId, viewModel.name, viewModel.quantity)
    }

    private fun startEditingProduct() {
        launch(UI) {
            try {
                view?.showProgress()
                val product = async(CommonPool) { getProduct.execute(viewModel.productId) }.await()
                viewModel.name = product.name
                viewModel.quantity = product.quantity

                view?.disableKeyboardOnStart()
                updateInputTexts()
                updateContentViews()

            } catch (e: Exception) {
                Log.e("LOG", "Error on startEditingProduct", e)
                view?.showErrorMessage(R.string.msg_error)
            } finally {
                view?.hideProgress()
            }
        }
    }

    private fun save(product: Product) {
        launch(UI) {
            try {
                view?.showProgress()

                val invalidFields = validateProduct.execute(product)
                if (invalidFields.isNotEmpty()) {
                    handleInvalidProducFields(invalidFields)
                    return@launch
                }

                async(CommonPool) { saveProduct.execute(product) }.await()

                val msg = if (hasProductToEdit()) {
                    R.string.msg_feedback_product_edited_successfully
                } else {
                    R.string.msg_feedback_product_inserted_successfully
                }

                view?.closeWithSuccess(msg)
            } catch (e: Exception) {
                Log.e("LOG", "Error on startEditingProduct", e)
                view?.showErrorMessage(R.string.msg_error)
                view?.closeWithFailure()
            } finally {
                view?.hideProgress()
            }
        }

    }

    private fun updateContentViews() {
        updateTitle()
    }

    private fun updateTitle() {
        if (hasProductToEdit()) {
            view?.setTitle(R.string.title_edit_product)
        } else {
            view?.setTitle(R.string.title_new_product)
        }
    }

    private fun hasProductToEdit() = viewModel.productId != EntityHelper.NO_ID

    private fun updateInputTexts() {
        view?.setName(viewModel.name)
        view?.setQuantity(viewModel.quantity.toString())
    }

    private fun handleInvalidProducFields(invalidFields: List<ValidateProduct.InvalidFields>) {
        invalidFields?.run() {
            if (size > 0) {

                when (get(0)) {
                    ProductValidator.InvalidProductFields.NAME_NOT_FILLED -> {
                        view?.showErrorMessage(R.string.error_msg_product_name_not_filled)
                    }
                    ProductValidator.InvalidProductFields.QUANTITY_NEGATIVE -> {
                        view?.showErrorMessage(R.string.product_quantity_cant_be_negative)
                    }
                }

            }
        }
    }


    interface View : BasePresenter.View {
        fun setTitle(title: Int)
        fun getName(): String
        fun getQuantity(): String
        fun setName(name: String)
        fun setQuantity(quantity: String)
    }

}


