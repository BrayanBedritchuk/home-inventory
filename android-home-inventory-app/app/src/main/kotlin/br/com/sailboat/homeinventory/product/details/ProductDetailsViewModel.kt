package br.com.sailboat.homeinventory.view.product.details

import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.homeinventory.helper.model.RecyclerViewItem

class ProductDetailsViewModel {

    var productId: Long = EntityHelper.NO_ID
    val productDetails = ArrayList<RecyclerViewItem>()

}