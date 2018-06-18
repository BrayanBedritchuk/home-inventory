package br.com.sailboat.homeinventory.ui.shopping

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.homeinventory.ui.model.ProductView
import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem
import br.com.sailboat.homeinventory.ui.model.ViewType
import br.com.sailboat.homeinventory.ui.model.viewholder.ShoppingItemViewHolder


class ShoppingAdapter(val callback: ShoppingAdapter.Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.PRODUCT.ordinal -> ShoppingItemViewHolder(parent, callback)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemCount() = callback.getShoppingItems().size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ShoppingItemViewHolder -> holder.bindItem(callback.getShoppingItems()[position] as ProductView)
        }
    }

    override fun getItemViewType(position: Int) = callback.getShoppingItems()[position].viewType


    interface Callback : ShoppingItemViewHolder.Callback {
        fun getShoppingItems(): List<RecyclerViewItem>
    }

}