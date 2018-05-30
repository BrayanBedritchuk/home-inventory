package br.com.sailboat.homeinventory.ui.base

import android.arch.lifecycle.MutableLiveData
import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.failure.Failure
import br.com.sailboat.homeinventory.helper.Event

abstract class BaseViewModel {

    var firstSession = true
    var failure: MutableLiveData<Failure> = MutableLiveData()
    var error = MutableLiveData<Event<String>>()
    val startAsync = MutableLiveData<Event<None>>()
    val finishAsync = MutableLiveData<Event<None>>()

    fun notifyError(msg: String) {
        error.value = Event(msg)
    }

    fun notifyStartAsync() {
        startAsync.value = Event(None())
    }

    fun notifyFinishAsync() {
        finishAsync.value = Event(None())
    }

}