package br.com.sailboat.homeinventory.data.model

import br.com.sailboat.canoe.helper.EntityHelper
import java.util.*

data class ShoppingData(
    var id: Long = EntityHelper.NO_ID,
    var establishmentId: Long = EntityHelper.NO_ID,
    val dateTime: Calendar
)