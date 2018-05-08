package br.com.sailboat.homeinventory.model.viewholder

import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.homeinventory.model.RecyclerViewItem

class ProductDetailsViewModel {

    var productId: Long = EntityHelper.NO_ID
    val productDetails = ArrayList<RecyclerViewItem>()

}