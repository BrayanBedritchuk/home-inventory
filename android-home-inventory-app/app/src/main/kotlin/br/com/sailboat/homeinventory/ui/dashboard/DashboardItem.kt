package br.com.sailboat.homeinventory.ui.dashboard

import android.app.Activity
import br.com.sailboat.homeinventory.ui.product.ProductDashboardItem

enum class DashboardItem(val callback: Callback) {

    PRODUCT(ProductDashboardItem());


    interface Callback {
        val iconId: Int
        fun onClickItem(activity: Activity)
    }

}