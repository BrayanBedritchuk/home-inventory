package br.com.sailboat.homeinventory.ui.product.details

import br.com.sailboat.homeinventory.domain.entity.EntityHelper
import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem
import javax.inject.Inject

class ProductDetailsViewModel @Inject constructor() {

    var productId = EntityHelper.NO_ID
    val productDetails = mutableListOf<RecyclerViewItem>()

}