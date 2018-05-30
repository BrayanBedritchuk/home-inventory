package br.com.sailboat.homeinventory.ui.product.list

import android.content.Context
import android.content.Intent
import br.com.sailboat.homeinventory.ui.base.BaseActivity

class ProductListActivity : BaseActivity() {

    companion object {
        fun startFrom(context: Context) {
            val intent = Intent(context, ProductListActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun newFragmentInstance() = ProductListFragment()

}