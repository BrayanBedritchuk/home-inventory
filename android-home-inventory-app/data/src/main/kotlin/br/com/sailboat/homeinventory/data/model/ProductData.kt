package br.com.sailboat.homeinventory.data.model

import br.com.sailboat.canoe.helper.EntityHelper

class ProductData(
    var id: Long = EntityHelper.NO_ID,
    val name: String
)