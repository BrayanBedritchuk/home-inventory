package br.com.sailboat.homeinventory.view.product.details

import android.content.Intent
import android.support.v4.app.Fragment
import br.com.sailboat.canoe.base.BaseActivitySingleFragment
import br.com.sailboat.homeinventory.helper.ExtrasHelper
import br.com.sailboat.homeinventory.helper.RequestCodeHelper

class ProductDetailsActivity : BaseActivitySingleFragment<ProductDetailsFragment>() {

    companion object {
        fun start(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductDetailsActivity::class.java)
            ExtrasHelper.putProductId(intent, productId)
            fragment.startActivityForResult(intent, RequestCodeHelper.PRODUCT_DETAILS.ordinal)
        }
    }

    override fun newFragmentInstance() : ProductDetailsFragment {
        val productId = ExtrasHelper.getProductId(intent)
        return ProductDetailsFragment.newInstance(
            productId
        )
    }

}

