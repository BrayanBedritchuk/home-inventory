package br.com.sailboat.homeinventory.view.shop

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.EditText
import android.widget.TextView
import br.com.sailboat.canoe.base.BaseDialogFragment
import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.homeinventory.R

class ShopProductDialog : BaseDialogFragment() {

    var productId: Long = EntityHelper.NO_ID
    var quantity: Int = 0

    companion object {
        fun show(manager: FragmentManager, productId: Long, quantity: Int) {
            val dialog = ShopProductDialog()
            dialog.productId = productId
            dialog.quantity = quantity
            dialog.show(manager, ShopProductDialog::class.java.name)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(activity, R.layout.dlg_shop_product, null)
        initViews(view)
        return buildDialog(view)
    }

    private fun initViews(view: View) {
        val tvProductName = view.findViewById<TextView>(R.id.tvProductName)
        tvProductName.text = "Product Name"

        val etQuantity = view.findViewById<EditText>(R.id.etQuantity)
        etQuantity.setText(quantity.toString())
    }

    private fun buildDialog(view: View): Dialog {
        val builder = AlertDialog.Builder(activity)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok, null)

        builder.setNegativeButton(R.string.cancel, null)

        return builder.create()
    }

}