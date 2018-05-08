package br.com.sailboat.homeinventory.view.shopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.helper.base.BaseActivity
import br.com.sailboat.homeinventory.shopping.ShoppingFragment

class ShoppingActivity : BaseActivity() {

    companion object {
        fun startFrom(activity: AppCompatActivity) {
            val intent = Intent(activity, ShoppingActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun newFragmentInstance() = ShoppingFragment()

}