package br.com.sailboat.homeinventory.ui.dialog

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import br.com.sailboat.homeinventory.ui.base.BaseDialogFragment

class MessageDialog : BaseDialogFragment() {

    var title: Int? = null
    var message: Int? = null

    companion object {
        fun show(manager: FragmentManager, title: Int?, message: Int?) {
            val dialog = MessageDialog()
            dialog.title = title
            dialog.message = message

            dialog.show(manager, "MESSAGE_DIALOG")
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        activity?.let {
            val builder = AlertDialog.Builder(it)
            title?.let { builder.setTitle(it) }
            message?.let { builder.setMessage(it) }
            builder.setPositiveButton(android.R.string.ok, null)

            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }

}