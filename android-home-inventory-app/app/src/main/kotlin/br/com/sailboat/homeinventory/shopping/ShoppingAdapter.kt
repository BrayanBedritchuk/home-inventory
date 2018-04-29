package br.com.sailboat.homeinventory.view.shopping

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.homeinventory.helper.ViewType
import br.com.sailboat.homeinventory.presentation.model.ProductModel
import java.lang.RuntimeException


class ShoppingAdapter(var callback: Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.PRODUCT.ordinal -> ShoppingItemViewHolder.newInstance(parent, callback)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemCount() = callback.getProducts().size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        when (holder) {
            is ShoppingItemViewHolder -> holder.bindItem(item = callback.getProducts()[position])
        }
    }

    interface Callback : ShoppingItemViewHolder.Callback {
        fun getProducts(): List<ProductModel>
    }

}