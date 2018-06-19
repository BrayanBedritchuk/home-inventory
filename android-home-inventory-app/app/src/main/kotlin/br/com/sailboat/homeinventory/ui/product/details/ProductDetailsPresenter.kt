package br.com.sailboat.homeinventory.ui.product.details

import android.content.Intent
import br.com.sailboat.homeinventory.R
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
) : BasePresenter<ProductDetailsPresenter.View>() {

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
                view?.closeWithFailure(R.string.msg_error_details)
            } finally {
                view?.hideProgress()
            }
        }
    }

    override fun postResult(requestCode: Int, data: Intent?) {
        super.postResult(requestCode, data)
        loadDetails()
    }

    fun onClickEdit() {
        view?.showEditProduct(viewModel.productId)
    }

    fun onClickDelete() {

    }

    fun getProductDetails() = viewModel.productDetails


    interface View : BasePresenter.View {
        fun extractProductId(): Long
        fun showEditProduct(productId: Long)
        fun updateDetails()
    }

}