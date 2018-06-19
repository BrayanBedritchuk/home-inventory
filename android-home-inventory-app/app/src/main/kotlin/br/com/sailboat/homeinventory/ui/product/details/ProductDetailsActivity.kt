package br.com.sailboat.homeinventory.ui.product.details

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import br.com.sailboat.homeinventory.ui.base.BaseActivity
import br.com.sailboat.homeinventory.ui.helper.Extras
import br.com.sailboat.homeinventory.ui.helper.RequestCode

class ProductDetailsActivity : BaseActivity() {

    companion object {
        fun startFrom(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductDetailsActivity::class.java)
            val args = Bundle()

            Extras.putProductId(args, productId)
            Extras.putBundle(intent, args)

            fragment.startActivityForResult(intent, RequestCode.PRODUCT_DETAILS.ordinal)
        }
    }

    override fun newFragmentInstance() = ProductDetailsFragment().also { it.arguments = Extras.getBundle(intent) }

}
