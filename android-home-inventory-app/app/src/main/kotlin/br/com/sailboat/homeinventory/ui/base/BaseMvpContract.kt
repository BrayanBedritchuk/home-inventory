package br.com.sailboat.homeinventory.ui.base

interface BaseMvpContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun closeKeyboard()
        fun disableKeyboardOnStart()
        fun closeWithSuccess()
        fun closeWithFailure()
        fun closeWithFailureDefaultMessage()
        fun logError(e: Exception)
    }

    interface Presenter {
        fun setView(view: BaseMvpContract.View?)
        fun onViewCreated()
        fun onDestroyView()
        fun postResult()
    }

}