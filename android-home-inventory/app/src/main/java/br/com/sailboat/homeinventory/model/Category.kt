package br.com.sailboat.homeinventory.model

import br.com.sailboat.canoe.helper.EntityHelper

data class Category(
    var id: Long = EntityHelper.NO_ID,
    val name: String
)