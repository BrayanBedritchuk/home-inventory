package br.com.sailboat.homeinventory.ui.model.viewholder

import android.support.v4.content.ContextCompat
import android.view.View
import android.view.ViewGroup
import br.com.sailboat.canoe.base.BaseViewHolder
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.model.ProductView
import kotlinx.android.synthetic.main.vh_product.view.*

class ProductViewHolder(itemView: View, onClickProduct: (position: Int) -> Unit) : BaseViewHolder(itemView) {

    companion object {
        fun newInstance(parent: ViewGroup?, onClickProduct: (position: Int) -> Unit): ProductViewHolder {
            val view = inflateLayout(parent, R.layout.vh_product)
            return ProductViewHolder(view, onClickProduct)
        }
    }

    init {
        itemView.setOnClickListener {
            onClickProduct(adapterPosition)
        }
    }

    fun bindItem(item: ProductView) {
        itemView.tvName.text = item.name
        itemView.tvQuantity.text = item.quantity.toString()
        initColorOfQuantity(item)
    }

    private fun initColorOfQuantity(item: ProductView) {
        val color = if (item.quantity == 0) {
            R.color.grey_40
        } else {
            R.color.md_teal_300
        }

        itemView.tvQuantity.setTextColor(ContextCompat.getColor(itemView.context, color))
    }


}