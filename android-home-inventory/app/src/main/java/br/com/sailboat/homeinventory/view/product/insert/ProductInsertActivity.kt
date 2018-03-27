package br.com.sailboat.homeinventory.view.product.insert

import android.content.Intent
import android.support.v4.app.Fragment
import br.com.sailboat.canoe.base.BaseActivitySingleFragment
import br.com.sailboat.homeinventory.helper.ExtrasHelper
import br.com.sailboat.homeinventory.helper.RequestCodeHelper

class ProductInsertActivity : BaseActivitySingleFragment<ProductInsertFragment>() {

    companion object {
        fun start(fragment: Fragment) {
            val intent = Intent(fragment.activity, ProductInsertActivity::class.java)
            fragment.startActivityForResult(intent, RequestCodeHelper.PRODUCT_INSERT.ordinal)
        }

        fun startToEdit(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductInsertActivity::class.java)
            ExtrasHelper.putProductId(intent, productId)
            fragment.startActivityForResult(intent, RequestCodeHelper.PRODUCT_INSERT.ordinal)
        }
    }

    override fun newFragmentInstance() : ProductInsertFragment {
        if (ExtrasHelper.hasProductId(intent)) {
            val productId = ExtrasHelper.getProductId(intent)
            return ProductInsertFragment.newInstanceWithProductToEdit(productId)
        } else {
            return ProductInsertFragment.newInstance()
        }
    }

}

