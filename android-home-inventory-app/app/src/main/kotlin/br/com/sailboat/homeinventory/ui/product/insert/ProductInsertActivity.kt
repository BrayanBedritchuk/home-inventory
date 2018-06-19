package br.com.sailboat.homeinventory.ui.product.insert

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import br.com.sailboat.homeinventory.ui.base.BaseActivity
import br.com.sailboat.homeinventory.ui.helper.Extras
import br.com.sailboat.homeinventory.ui.helper.RequestCode

class ProductInsertActivity : BaseActivity() {

    companion object {
        fun startFrom(fragment: Fragment) {
            val intent = Intent(fragment.activity, ProductInsertActivity::class.java)
            fragment.startActivityForResult(intent, RequestCode.PRODUCT_INSERT.ordinal)
        }

        fun startEdit(fragment: Fragment, productId: Long) {
            val intent = Intent(fragment.activity, ProductInsertActivity::class.java)
            val args = Bundle()
            Extras.putProductId(args, productId)
            Extras.putBundle(intent, args)

            fragment.startActivityForResult(intent, RequestCode.PRODUCT_INSERT.ordinal)
        }
    }

    override fun newFragmentInstance() = ProductInsertFragment().also { it.arguments = Extras.getBundle(intent) }

}

