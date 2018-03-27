package br.com.sailboat.homeinventory.view.product.details

import android.os.Bundle
import br.com.sailboat.canoe.base.BasePresenter
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.canoe.helper.LogHelper
import br.com.sailboat.canoe.recycler.RecyclerItem
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.helper.ExtrasHelper
import br.com.sailboat.homeinventory.interactor.loader.ProductLoader
import java.util.*


class ProductDetailsPresenter(view: View) : BasePresenter<ProductDetailsPresenter.View>(view) {

    val viewModel = ProductDetailsViewModel()

    override fun extractExtrasFromArguments(arguments: Bundle?) {
        arguments?.let {
            val productId = ExtrasHelper.getProductId(it)
            viewModel.productId = productId
        }
    }

    override fun postResume() {
        loadProductDetails()
    }

    override fun onClickFab() {
        view.startEditingProduct(viewModel.productId)
    }

    fun postResult() {
        loadProductDetails()
    }

    private fun loadProductDetails() {
        AsyncHelper.execute(object : AsyncHelper.Callback {

            internal var details: List<RecyclerItem> = ArrayList<RecyclerItem>()

            @Throws(Exception::class)
            override fun doInBackground() {
                details = ProductLoader(context).loadProductDetails(viewModel.productId)
            }

            override fun onSuccess() {
                viewModel.productDetails.clear()
                viewModel.productDetails.addAll(details)
                updateContentViews()
            }

            override fun onFail(e: Exception) {
                LogHelper.logException(e)
                view.showToast(getString(R.string.msg_exception_product_details))
                view.closeActivityWithResultCanceled()
            }

        })

    }

    private fun updateContentViews() {
        view.updateRecycler()
    }


    interface View : BasePresenter.View {
        fun startEditingProduct(productId: Long)
    }


}