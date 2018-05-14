package br.com.sailboat.homeinventory.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.product.list.ProductListActivity
import br.com.sailboat.homeinventory.ui.shopping.ShoppingActivity
import kotlinx.android.synthetic.main.act_dashboard.*

class DashboardActivity : AppCompatActivity() {

    companion object {
        fun startFrom(activity: AppCompatActivity) {
            val intent = Intent(activity, DashboardActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_dashboard)
        initViews()
    }

    private fun initViews() {
        findViewById<Toolbar>(R.id.toolbar).setTitle(R.string.app_name)

        // TODO: Use RecyclerView instead of buttons
        btShop.setOnClickListener { ShoppingActivity.startFrom(this) }
        btProducts.setOnClickListener { ProductListActivity.startFrom(this) }

//        AsyncHelper.execute(object : AsyncHelper.Callback {
//
//            override fun doInBackground() {
//
//                val productRepo = ProductRoomRepository(application)
//
//                for (i in 1..100) {
//                    productRepo.insert(ProductData(name = "Product $i", quantity = i))
//                }
//
//
//            }
//
//            override fun onSuccess() {
//
//            }
//
//            override fun onFail(throwable: Throwable) {
//
//            }
//        })


    }

}