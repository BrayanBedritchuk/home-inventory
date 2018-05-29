package br.com.sailboat.homeinventory.ui.shopping

import android.app.Dialog
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import br.com.sailboat.canoe.base.BaseDialogFragment
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.data.ProductData

class ShoppingProductDialog : BaseDialogFragment() {

    lateinit var product: ProductData
    lateinit var onClickOk: (productId: Long, quantity: Int?) -> Unit
    var quantity: Int = 0
    lateinit var etQuantity: EditText

    companion object {
        fun show(
            manager: FragmentManager,
            product: ProductData,
            quantity: Int,
            onClickOk: (productId: Long, quantity: Int?) -> Unit
        ) {
            val dialog = ShoppingProductDialog()
            dialog.product = product
            dialog.quantity = quantity
            dialog.onClickOk = onClickOk
            dialog.show(manager, ShoppingProductDialog::class.java.name)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val view = View.inflate(activity, R.layout.dlg_shop_product, null)
        initViews(view)

        val alert = buildDialog(view)

        alert.getWindow()!!.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)

        return alert
    }

    private fun initViews(view: View) {
        val tvProductName = view.findViewById<TextView>(R.id.tvProductName)
        tvProductName.text = product.name

        etQuantity = view.findViewById(R.id.etQuantity)
        etQuantity.setText(quantity.toString())
    }

    private fun buildDialog(view: View): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        builder.setView(view)
        builder.setPositiveButton(android.R.string.ok, { _, _ ->
            val quantity = etQuantity.text.trim().toString().toIntOrNull()
            onClickOk.invoke(product.id, quantity)
        })

        builder.setNegativeButton(R.string.cancel, null)

        return builder.create()
    }

}