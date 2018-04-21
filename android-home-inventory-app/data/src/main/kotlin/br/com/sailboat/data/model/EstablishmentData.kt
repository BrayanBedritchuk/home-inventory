package br.com.sailboat.homeinventory.data.model

import br.com.sailboat.canoe.helper.EntityHelper

class EstablishmentData(
    var id: Long = EntityHelper.NO_ID,
    val name: String
)