package br.com.sailboat.homeinventory.ui.product.details

import android.content.Intent
import android.os.Bundle
import br.com.sailboat.canoe.base.BaseFragment
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.helper.Extras
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertActivity

class ProductDetailsFragment : BaseFragment<ProductDetailsPresenter>(),
    ProductDetailsPresenter.View,
    ProductDetailsAdapter.Callback {


    companion object {

        fun newInstance(productId: Long): ProductDetailsFragment {
            val args = Bundle()
            Extras.putProductId(args, productId)
            val fragment =
                ProductDetailsFragment()
            fragment.arguments = args

            return fragment
        }

    }


    override fun getLayoutId() = R.layout.frg_details

    override fun newPresenterInstance() =
        ProductDetailsPresenter(
            this,
            null
        )

    override fun onInitToolbar() {
        toolbar.setTitle(R.string.title_product_details)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            activity!!.onBackPressed()
        }
    }

    override fun onInitFab() {
        fab.setImageResource(R.drawable.ic_edit_white_24dp)
    }

    override fun onInitRecycler() {
        recycler.adapter =
                ProductDetailsAdapter(this)
    }

    override fun postActivityResult(requestCode: Int, data: Intent?) {
        presenter.postResult()
    }

    override fun getProductDetails() = presenter.viewModel.productDetails

    override fun startEditingProduct(productId: Long) {
        ProductInsertActivity.startToEditFrom(this, productId)
    }


}