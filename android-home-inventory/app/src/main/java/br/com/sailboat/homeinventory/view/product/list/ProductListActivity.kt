package br.com.sailboat.homeinventory.view.product.list

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.canoe.base.BaseActivitySingleFragment
import br.com.sailboat.homeinventory.helper.RequestCodeHelper

class ProductListActivity : BaseActivitySingleFragment<ProductListFragment>() {

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ProductListActivity::class.java)
            activity.startActivityForResult(intent, RequestCodeHelper.PRODUCT_LIST.ordinal)
        }
    }

    override fun newFragmentInstance() = ProductListFragment()

}

