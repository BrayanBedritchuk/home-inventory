package br.com.sailboat.homeinventory.ui.helper

import android.app.Activity
import android.content.Context
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager

object UIHelper {

    fun closeKeyboard(activity: Activity?) {
        val view = activity?.currentFocus
        view?.let { getInputManager(activity).hideSoftInputFromWindow(view.windowToken, 0) }
    }

    fun disableKeyboardOnStart(activity: Activity?) {
        activity?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN)
    }

    fun getInputManager(activity: Activity?): InputMethodManager {
        return activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

}