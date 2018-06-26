package br.com.sailboat.homeinventory.ui.product.insert

import br.com.sailboat.homeinventory.domain.entity.EntityHelper
import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.usecase.product.GetProduct
import br.com.sailboat.homeinventory.domain.usecase.product.SaveProduct
import br.com.sailboat.homeinventory.domain.usecase.product.ValidateProduct
import br.com.sailboat.homeinventory.ui.base.BasePresenter
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
) : BasePresenter<ProductInsertContract.View>(), ProductInsertContract.Presenter {

    override fun create() {
        extractArgs()
        if (hasProductToEdit()) {
            startEditingProduct()
        } else {
            updateContentViews()
        }
    }

    override fun restart() {
        updateContentViews()
    }

    override fun onClickSave() {
        try {
            view?.closeKeyboard()
            extractInfoFromViews()
            val product = buildProductFromViewModel()
            save(product)
        } catch (e: Exception) {
            view?.logError(e)
            view?.showErrorOnSaveProduct()
        }
    }

    private fun extractInfoFromViews() {
        viewModel.name = view?.getName() ?: ""
        viewModel.quantity = view?.getQuantity()?.toIntOrNull() ?: 0
    }

    private fun buildProductFromViewModel(): Product {
        return Product(viewModel.productId, viewModel.name, viewModel.quantity)
    }

    private fun extractArgs() {
        viewModel.productId = view?.extractProductId() ?: EntityHelper.NO_ID
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
                view?.logError(e)
                view?.closeWithFailureDefaultMessage()
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

                if (hasProductToEdit()) {
                    view?.closeWithSuccessOnEditProduct()
                } else {
                    view?.closeWithSuccessOnInsertProduct()
                }
            } catch (e: Exception) {
                view?.logError(e)
                view?.showErrorOnSaveProduct()
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
            view?.setTitleEditProduct()
        } else {
            view?.setTitleNewProduct()
        }
    }

    private fun hasProductToEdit() = viewModel.productId != EntityHelper.NO_ID

    private fun updateInputTexts() {
        view?.setName(viewModel.name)
        view?.setQuantity(viewModel.quantity.toString())
    }

    private fun handleInvalidProducFields(invalidFields: List<ValidateProduct.InvalidFields>) {
        invalidFields.takeIf { it.isNotEmpty() }?.run {
            when (get(0)) {
                ValidateProduct.InvalidFields.NAME_NOT_FILLED -> {
                    view?.showErrorNameNotFilled()
                }
                ValidateProduct.InvalidFields.QUANTITY_NEGATIVE -> {
                    view?.showErrorQuantityNegative()
                }
            }
        }
    }

}


