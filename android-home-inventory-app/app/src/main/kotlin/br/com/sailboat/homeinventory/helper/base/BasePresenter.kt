package br.com.sailboat.homeinventory.helper.base

abstract class BasePresenter {

    open fun onCreateView() {}
    open fun onRestartView() {}

    interface View {
        fun showProgress()
        fun hideProgress()
    }
}