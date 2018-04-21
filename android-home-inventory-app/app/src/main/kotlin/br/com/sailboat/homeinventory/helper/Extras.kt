package br.com.sailboat.homeinventory.presentation.helper

import android.content.Intent
import android.os.Bundle
import br.com.sailboat.canoe.helper.EntityHelper

object Extras {

    const val PRODUCT_ID = "PRODUCT_ID"

    fun putProductId(intent: Intent, id: Long) {
        intent.putExtra(PRODUCT_ID, id)
    }

    fun putProductId(bundle: Bundle, id: Long) {
        bundle.putLong(PRODUCT_ID, id)
    }

    fun getProductId(intent: Intent) = intent.getLongExtra(PRODUCT_ID, EntityHelper.NO_ID)

    fun getProductId(bundle: Bundle) = bundle.getLong(PRODUCT_ID, EntityHelper.NO_ID)

    fun hasProductId(intent: Intent) = intent.hasExtra(PRODUCT_ID)

}