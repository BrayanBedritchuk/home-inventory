package br.com.sailboat.homeinventory.domain

import android.util.Log
import br.com.sailboat.homeinventory.core.Logger
import javax.inject.Inject

class LogcatLogger @Inject constructor() : Logger {

    override fun debug(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun error(tag: String, throwable: Throwable) {
        Log.e(tag, Log.getStackTraceString(throwable))
    }
}