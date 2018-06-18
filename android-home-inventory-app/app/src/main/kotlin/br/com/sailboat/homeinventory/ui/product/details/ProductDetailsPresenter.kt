package br.com.sailboat.homeinventory.ui.product.details

import android.content.Intent
import android.util.Log
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import br.com.sailboat.homeinventory.ui.helper.Extras
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class ProductDetailsPresenter @Inject constructor(
    private val viewModel: ProductDetailsViewModel,
    private val getProductDetails: GetProductDetails
) : BasePresenter<ProductDetailsPresenter.View>() {

    override fun extractArgs(intent: Intent?) {
        intent?.let {
            val productId = Extras.getProductId(it)
            viewModel.productId = productId
        }
    }

    override fun create() {
        loadProductDetails()
    }

    override fun restart() {
        view?.updateDetails()
    }

    private fun loadProductDetails() {
        launch(UI) {
            try {
                view?.showProgress()
                val details =
                    async(CommonPool) { getProductDetails.execute(viewModel.productId) }.await()
                viewModel.productDetails.clear()
                viewModel.productDetails.addAll(details)
                view?.updateDetails()

            } catch (e: Exception) {
                Log.e("ProductDetailsPresenter", "Error on load product details", e)
            } finally {
                view?.hideProgress()
            }
        }
    }

    override fun postResult(requestCode: Int, data: Intent?) {
        super.postResult(requestCode, data)
        loadProductDetails()
    }

    fun onClickEdit() {
        view?.showEditProduct(viewModel.productId)
    }

    fun onClickDelete() {

    }

    fun getProductDetails() = viewModel.productDetails


    interface View : BasePresenter.View {
        fun showEditProduct(productId: Long)
        fun updateDetails()
    }

}