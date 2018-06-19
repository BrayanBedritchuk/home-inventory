package br.com.sailboat.homeinventory.ui.helper

import android.content.Intent
import android.os.Bundle
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.domain.entity.EntityHelper

object Extras {

    private const val BUNDLE = "BUNDLE"
    private const val PRODUCT_ID = "PRODUCT_ID"
    private const val FEEDBACK_MESSAGE = "FEEDBACK_MESSAGE"
    private const val ERROR_MESSAGE = "ERROR_MESSAGE"

    fun putBundle(intent: Intent?, bundle: Bundle) = intent?.putExtra(BUNDLE, bundle)
    fun getBundle(intent: Intent?) = intent?.getBundleExtra(BUNDLE)

    fun putProductId(bundle: Bundle?, id: Long) = bundle?.putLong(PRODUCT_ID, id)
    fun getProductId(bundle: Bundle?) = bundle?.getLong(PRODUCT_ID, EntityHelper.NO_ID) ?: EntityHelper.NO_ID

    fun putFeedbackMessage(intent: Intent?, msgId: Int) = intent?.putExtra(FEEDBACK_MESSAGE, msgId)
    fun getFeedbackMessage(intent: Intent?) = intent?.getIntExtra(FEEDBACK_MESSAGE, R.string.default_string)
    fun hasFeedbackMessage(intent: Intent?) = intent?.hasExtra(FEEDBACK_MESSAGE) ?: false
    fun putErrorMessage(intent: Intent?, msgId: Int) = intent?.putExtra(ERROR_MESSAGE, msgId)
    fun getErrorMessage(intent: Intent?) = intent?.getIntExtra(ERROR_MESSAGE, R.string.default_string)
    fun hasErrorMessage(intent: Intent?) = intent?.hasExtra(ERROR_MESSAGE) ?: false

}