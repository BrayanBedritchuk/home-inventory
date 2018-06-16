package br.com.sailboat.homeinventory.ui.helper

import android.content.Intent
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.domain.entity.EntityHelper

object Extras {

    private const val PRODUCT_ID = "PRODUCT_ID"
    private const val FEEDBACK_MESSAGE = "FEEDBACK_MESSAGE"

    fun putProductId(intent: Intent?, id: Long) = intent?.putExtra(PRODUCT_ID, id)

    fun getProductId(intent: Intent?) = intent?.getLongExtra(PRODUCT_ID, EntityHelper.NO_ID) ?: EntityHelper.NO_ID

    fun hasProductId(intent: Intent?) = intent?.hasExtra(PRODUCT_ID) ?: false

    fun putFeedbackMessage(intent: Intent?, msgId: Int) = intent?.putExtra(FEEDBACK_MESSAGE, msgId)

    fun getFeedbackMessage(intent: Intent?) = intent?.getIntExtra(FEEDBACK_MESSAGE, R.string.default_string)

    fun hasFeedbackMessage(intent: Intent?) = intent?.hasExtra(FEEDBACK_MESSAGE) ?: false

}