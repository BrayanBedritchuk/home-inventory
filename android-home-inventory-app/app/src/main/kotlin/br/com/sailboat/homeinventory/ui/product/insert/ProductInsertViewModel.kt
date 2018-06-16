package br.com.sailboat.homeinventory.ui.product.insert

import br.com.sailboat.homeinventory.domain.entity.EntityHelper

class ProductInsertViewModel {

    var productId = EntityHelper.NO_ID
    var name: String = ""
    var quantity: Int = 0
}