package br.com.sailboat.homeinventory.view.product.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.helper.ViewType


class ProductListAdapter(var callback: Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.PRODUCT.ordinal -> ProductViewHolder.newInstance(parent, callback)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemCount() = callback.getProducts().size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.bindItem(item = callback.getProducts()[position])
        }
    }

    interface Callback : ProductViewHolder.Callback {
        fun getProducts(): List<Product>
    }

}