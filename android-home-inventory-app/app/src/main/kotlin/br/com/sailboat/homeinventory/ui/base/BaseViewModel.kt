package br.com.sailboat.homeinventory.ui.base

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    open fun init() {}
}