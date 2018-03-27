package br.com.sailboat.homeinventory.model

import br.com.sailboat.canoe.helper.EntityHelper

data class Product(
    var id: Long = EntityHelper.NO_ID,
    var name: String,
    var quantity: Int = 0
)