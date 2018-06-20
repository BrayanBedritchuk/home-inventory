package br.com.sailboat.homeinventory.ui.shopping

import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.ui.base.BaseActivity
import br.com.sailboat.homeinventory.ui.helper.RequestCode

class ShoppingActivity : BaseActivity() {

    companion object {
        fun startFrom(activity: AppCompatActivity) {
            val intent = Intent(activity, ShoppingActivity::class.java)
            activity.startActivity(intent)
        }

        fun startFrom(fragment: Fragment) {
            val intent = Intent(fragment.activity, ShoppingActivity::class.java)
            fragment.startActivityForResult(intent, RequestCode.SHOPPING.ordinal)
        }
    }

    override fun newFragmentInstance() = ShoppingFragment()

}