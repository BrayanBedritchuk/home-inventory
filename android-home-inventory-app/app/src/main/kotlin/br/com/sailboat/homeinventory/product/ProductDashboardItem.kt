package br.com.sailboat.homeinventory.view.product

import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.view.dashboard.DashboardItem
import br.com.sailboat.homeinventory.view.product.list.ProductListActivity

class ProductDashboardItem : DashboardItem.Callback {

    override val iconId = R.drawable.ic_edit_white_24dp

    override fun onClickItem(activity: AppCompatActivity) {
        ProductListActivity.startFrom(activity)
    }

}