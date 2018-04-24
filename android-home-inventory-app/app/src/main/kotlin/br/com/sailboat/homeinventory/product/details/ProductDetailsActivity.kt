package br.com.sailboat.homeinventory.view.product.details

import android.content.Intent
import android.support.v4.app.Fragment
import br.com.sailboat.canoe.base.BaseActivitySingleFragment
import br.com.sailboat.homeinventory.presentation.helper.Extras
import br.com.sailboat.homeinventory.presentation.helper.RequestCode

class ProductDetailsActivity : BaseActivitySingleFragment<ProductDetailsFragment>() {

    companion object {
        fun start(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductDetailsActivity::class.java)
            Extras.putProductId(intent, productId)
            fragment.startActivityForResult(intent, RequestCode.PRODUCT_DETAILS.ordinal)
        }
    }

    override fun newFragmentInstance() : ProductDetailsFragment {
        val productId = Extras.getProductId(intent)
        return ProductDetailsFragment.newInstance(
            productId
        )
    }

}
