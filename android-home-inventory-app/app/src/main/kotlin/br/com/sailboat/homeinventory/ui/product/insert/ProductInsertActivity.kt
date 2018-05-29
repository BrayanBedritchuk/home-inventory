package br.com.sailboat.homeinventory.ui.product.insert

import android.content.Intent
import android.support.v4.app.Fragment
import br.com.sailboat.canoe.base.BaseActivitySingleFragment
import br.com.sailboat.homeinventory.ui.Extras
import br.com.sailboat.homeinventory.ui.RequestCode

class ProductInsertActivity : BaseActivitySingleFragment<ProductInsertFragment>() {

    companion object {
        fun startFrom(fragment: Fragment) {
            val intent = Intent(fragment.activity, ProductInsertActivity::class.java)
            fragment.startActivityForResult(intent, RequestCode.PRODUCT_INSERT.ordinal)
        }

        fun startToEditFrom(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductInsertActivity::class.java)
            Extras.putProductId(intent, productId)
            fragment.startActivityForResult(intent, RequestCode.PRODUCT_INSERT.ordinal)
        }
    }

    override fun newFragmentInstance() : ProductInsertFragment {
        if (Extras.hasProductId(intent)) {
            val productId = Extras.getProductId(intent)
            return ProductInsertFragment.newInstanceWithProductToEdit(
                productId
            )
        } else {
            return ProductInsertFragment.newInstance()
        }
    }

}

