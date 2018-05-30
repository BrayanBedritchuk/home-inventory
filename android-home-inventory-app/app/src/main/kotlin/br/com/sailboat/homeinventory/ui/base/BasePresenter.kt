package br.com.sailboat.homeinventory.ui.base

abstract class BasePresenter {

    var firstSession = true

    fun onViewCreated() {
        if (firstSession) {
            create()
            firstSession = false
        } else {
            restart()
        }
    }

    protected open fun create() {}
    protected open fun restart() {}

}