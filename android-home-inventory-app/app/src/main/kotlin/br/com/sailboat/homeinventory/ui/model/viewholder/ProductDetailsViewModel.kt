package br.com.sailboat.homeinventory.ui.model.viewholder

import br.com.sailboat.homeinventory.domain.entity.EntityHelper
import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem

class ProductDetailsViewModel {

    var productId = EntityHelper.NO_ID
    val productDetails = mutableListOf<RecyclerViewItem>()

}