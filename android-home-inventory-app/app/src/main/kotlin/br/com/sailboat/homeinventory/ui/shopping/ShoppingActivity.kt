package br.com.sailboat.homeinventory.ui.shopping

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.ui.base.BaseActivity

class ShoppingActivity : BaseActivity() {

    companion object {
        fun startFrom(activity: AppCompatActivity) {
            val intent = Intent(activity, ShoppingActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun newFragmentInstance() = ShoppingFragment()

}