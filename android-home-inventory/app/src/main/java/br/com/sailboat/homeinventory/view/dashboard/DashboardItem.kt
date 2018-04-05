package br.com.sailboat.homeinventory.view.dashboard

import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.view.product.ProductDashboardItem

enum class DashboardItem(val callback: DashboardItem.Callback) {

    PRODUCT(ProductDashboardItem());


    interface Callback {
        val iconId: Int
        fun onClickItem(activity: AppCompatActivity)
    }

}