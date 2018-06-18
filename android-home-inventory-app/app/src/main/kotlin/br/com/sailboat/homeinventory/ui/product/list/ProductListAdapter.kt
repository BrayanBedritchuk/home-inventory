package br.com.sailboat.homeinventory.ui.product.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.homeinventory.ui.model.ProductView
import br.com.sailboat.homeinventory.ui.model.ViewType
import br.com.sailboat.homeinventory.ui.model.viewholder.ProductViewHolder


class ProductListAdapter(val callback: ProductListAdapter.Callback) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        return when (viewType) {
            ViewType.PRODUCT.ordinal -> ProductViewHolder(parent, callback)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemCount() = callback.getProducts().size

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bindItem(callback.getProducts()[position])
    }


    interface Callback : ProductViewHolder.Callback {
        fun getProducts(): List<ProductView>
    }

}