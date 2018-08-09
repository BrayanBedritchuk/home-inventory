package br.com.sailboat.homeinventory.ui.dialog

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v7.app.AlertDialog
import br.com.sailboat.homeinventory.ui.base.BaseDialogFragment

class OptionDialog : BaseDialogFragment() {

    var title: Int? = null
    var message: Int? = null
    var onClickPositive: DialogInterface.OnClickListener? = null
    var onClickNegative: DialogInterface.OnClickListener? = null
    var yesOption = android.R.string.yes
    var noOption = android.R.string.no

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        activity?.let {
            val builder = AlertDialog.Builder(it)
            title?.let { builder.setTitle(it) }
            message?.let { builder.setMessage(it) }
            builder.setPositiveButton(yesOption, onClickPositive)
            builder.setNegativeButton(noOption, onClickNegative)

            return builder.create()
        }

        return super.onCreateDialog(savedInstanceState)
    }

}