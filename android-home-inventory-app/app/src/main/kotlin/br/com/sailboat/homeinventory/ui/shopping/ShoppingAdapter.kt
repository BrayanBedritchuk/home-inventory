package br.com.sailboat.homeinventory.ui.shopping

import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.view.ViewGroup
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.ui.model.viewholder.ShoppingItemViewHolder


class ShoppingAdapter(val callback: ShoppingItemViewHolder.Callback) :
    PagedListAdapter<ProductData, ShoppingItemViewHolder>(
        object : DiffUtil.ItemCallback<ProductData>() {

            override fun areItemsTheSame(oldItem: ProductData, newItem: ProductData) =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: ProductData?, newItem: ProductData?) =
                oldItem == newItem
        }

    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingItemViewHolder {
        return ShoppingItemViewHolder.newInstance(parent, callback)
    }

    override fun onBindViewHolder(holder: ShoppingItemViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it) }
    }

}