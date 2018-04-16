package br.com.sailboat.homeinventory.model

import br.com.sailboat.canoe.helper.EntityHelper
import java.util.*

data class Shopping(
    var id: Long = EntityHelper.NO_ID,
    var establishmentId: Long = EntityHelper.NO_ID,
    val dateTime: Calendar
)