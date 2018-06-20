package br.com.sailboat.homeinventory.ui.product.details

import br.com.sailboat.homeinventory.domain.entity.EntityHelper
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class ProductDetailsPresenter @Inject constructor(
    private val viewModel: ProductDetailsViewModel,
    private val getProductDetails: GetProductDetails
) : BasePresenter<ProductDetailsContract.View>(), ProductDetailsContract.Presenter {

    override fun create() {
        extractArgs()
        loadDetails()
    }

    override fun restart() {
        view?.updateDetails()
    }

    private fun extractArgs() {
        viewModel.productId = view?.extractProductId() ?: EntityHelper.NO_ID
    }

    private fun loadDetails() {
        launch(UI) {
            try {
                view?.showProgress()
                val details = async(CommonPool) { getProductDetails.execute(viewModel.productId) }.await()

                viewModel.productDetails.clear()
                viewModel.productDetails.addAll(details)

                view?.updateDetails()
            } catch (e: Exception) {
                view?.logError(e)
                view?.closeWithFailureOnLoadDetails()
            } finally {
                view?.hideProgress()
            }
        }
    }

    override fun postResult() {
        super.postResult()
        loadDetails()
    }

    override fun onClickEdit() {
        view?.navigateToEditProduct(viewModel.productId)
    }

    override fun onClickDelete() {

    }

    override fun getProductDetails() = viewModel.productDetails

}