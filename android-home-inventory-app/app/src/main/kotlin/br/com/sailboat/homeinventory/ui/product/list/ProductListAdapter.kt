package br.com.sailboat.homeinventory.ui.product.list

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.homeinventory.ui.model.ProductView
import br.com.sailboat.homeinventory.ui.model.ViewType
import br.com.sailboat.homeinventory.ui.model.viewholder.ProductViewHolder
import kotlin.properties.Delegates


class ProductListAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var collection: List<ProductView> by Delegates.observable(emptyList()) {
            _, _, _ -> notifyDataSetChanged()
    }

    lateinit var onClickProduct: (position: Int) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ViewType.PRODUCT.ordinal -> ProductViewHolder.newInstance(parent, onClickProduct)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemCount() = collection.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ProductViewHolder -> holder.bindItem(item = collection[position])
        }
    }

}