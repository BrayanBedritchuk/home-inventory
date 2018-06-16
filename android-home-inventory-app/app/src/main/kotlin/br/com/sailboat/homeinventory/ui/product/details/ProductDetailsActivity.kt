package br.com.sailboat.homeinventory.ui.product.details

import android.content.Intent
import android.support.v4.app.Fragment
import br.com.sailboat.homeinventory.ui.helper.Extras
import br.com.sailboat.homeinventory.ui.helper.RequestCode
import br.com.sailboat.homeinventory.ui.base.BaseActivity

class ProductDetailsActivity : BaseActivity() {

    companion object {
        fun startFrom(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductDetailsActivity::class.java)
            Extras.putProductId(intent, productId)
            fragment.startActivityForResult(intent, RequestCode.PRODUCT_DETAILS.ordinal)
        }
    }

    override fun newFragmentInstance() = ProductDetailsFragment()

}
