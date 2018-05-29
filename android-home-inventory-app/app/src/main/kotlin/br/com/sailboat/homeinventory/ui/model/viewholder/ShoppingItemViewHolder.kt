package br.com.sailboat.homeinventory.ui.model.viewholder

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import br.com.sailboat.canoe.base.BaseViewHolder
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.data.ProductData
import kotlinx.android.synthetic.main.vh_shopping_product.view.*


class ShoppingItemViewHolder(itemView: View, val callback: Callback) : BaseViewHolder(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup?, callback: Callback): ShoppingItemViewHolder {
            val view = inflateLayout(parent, R.layout.vh_shopping_product)
            return ShoppingItemViewHolder(
                view,
                callback
            )
        }
    }

    init {
        itemView.setOnClickListener {
            callback.onClickShoppingProduct(adapterPosition)
        }
    }

    fun bindItem(item: ProductData) {
        itemView.tvName.text = item.name
        itemView.tvQuantityInStock.text = item.quantity.toString()
        initColorOfQuantity(item)

        if (callback.wasPurchased(item.id)) {
            itemView.llShoppingCart.visibility = View.VISIBLE
            itemView.tvShoppingQuantity.text = callback.getShoppingQuantity(item.id)
        } else {
            itemView.llShoppingCart.visibility = View.GONE
        }

    }

    private fun initColorOfQuantity(item: ProductData) {
        val color = if (item.quantity == 0) {
            R.color.grey_40
        } else {
            R.color.md_teal_300
        }

        itemView.tvQuantityInStock.setTextColor(ContextCompat.getColor(itemView.context, color))
    }

    interface Callback {
        fun onClickShoppingProduct(position: Int)
        fun wasPurchased(productId: Long): Boolean
        fun getShoppingQuantity(productId: Long): String
    }

}