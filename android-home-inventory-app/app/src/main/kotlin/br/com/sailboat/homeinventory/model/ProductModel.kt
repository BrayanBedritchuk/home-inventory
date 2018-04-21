package br.com.sailboat.homeinventory.presentation.model

import br.com.sailboat.canoe.helper.EntityHelper

data class ProductModel(
    var id: Long = EntityHelper.NO_ID,
    val name: String,
    var quantity: Int = 0
)