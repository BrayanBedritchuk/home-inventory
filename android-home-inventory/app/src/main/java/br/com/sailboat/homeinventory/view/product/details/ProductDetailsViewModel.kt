package br.com.sailboat.homeinventory.view.product.details

import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.canoe.recycler.RecyclerItem

class ProductDetailsViewModel {

    var productId: Long = EntityHelper.NO_ID.toLong()
    val productDetails = ArrayList<RecyclerItem>()

}