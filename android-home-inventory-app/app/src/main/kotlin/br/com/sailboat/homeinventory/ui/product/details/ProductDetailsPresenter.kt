package br.com.sailboat.homeinventory.ui.product.details

import android.content.Intent
import android.util.Log
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import br.com.sailboat.homeinventory.ui.helper.Extras
import br.com.sailboat.homeinventory.ui.model.viewholder.ProductDetailsViewModel
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject


class ProductDetailsPresenter @Inject constructor(private val getProductDetails: GetProductDetails) :
    BasePresenter<ProductDetailsPresenter.View>() {

    val viewModel = ProductDetailsViewModel()

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
        loadProductDetails()
    }

    fun onClickEdit() {
        view?.showEditProduct(viewModel.productId)
    }

    fun onClickDelete() {

    }


    interface View : BasePresenter.View {
        fun showEditProduct(productId: Long)
        fun updateDetails()
    }


}