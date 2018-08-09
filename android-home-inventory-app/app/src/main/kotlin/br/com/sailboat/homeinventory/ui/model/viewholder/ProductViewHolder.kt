package br.com.sailboat.homeinventory.ui.model.viewholder

import android.support.v4.content.ContextCompat
import android.view.ViewGroup
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseViewHolder
import br.com.sailboat.homeinventory.ui.model.ProductView
import kotlinx.android.synthetic.main.vh_product.view.*

class ProductViewHolder(parent: ViewGroup, callback: ProductViewHolder.Callback) :
    BaseViewHolder<ProductView>(inflate(parent, R.layout.vh_product)) {

    init {
        itemView.setOnClickListener { callback.onClickProduct(adapterPosition) }
    }

    override fun bindItem(item: ProductView) {
        itemView.txtName.text = item.name
        itemView.txtQuantity.text = item.quantity.toString()
        initColorOfQuantity(item)
    }

    private fun initColorOfQuantity(item: ProductView) {
        val color = if (item.quantity == 0) {
            R.color.grey_40
        } else {
            R.color.md_teal_300
        }

        itemView.txtQuantity.setTextColor(ContextCompat.getColor(itemView.context, color))
    }


    interface Callback {
        fun onClickProduct(position: Int)
    }

}