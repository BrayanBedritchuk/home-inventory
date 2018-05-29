package br.com.sailboat.homeinventory.ui.model.viewholder

import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem

class ProductDetailsViewModel {

    var productId: Long = EntityHelper.NO_ID
    val productDetails = ArrayList<RecyclerViewItem>()

}