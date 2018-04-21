package br.com.sailboat.homeinventory.view.product.details

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.canoe.recycler.RecyclerItem
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem
import br.com.sailboat.canoe.recycler.item.TitleRecyclerItem
import br.com.sailboat.canoe.recycler.view_holder.LabelValueViewHolder
import br.com.sailboat.canoe.recycler.view_holder.TitleViewHolder
import br.com.sailboat.homeinventory.presentation.helper.ViewType


class ProductDetailsAdapter(var callback: Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            ViewType.TITLE.ordinal -> return TitleViewHolder.newInstance(parent)
            ViewType.LABEL_VALUE.ordinal -> return LabelValueViewHolder.newInstance(parent)
            else -> throw RuntimeException("ViewHolder not found")
        }

    }

    override fun getItemCount() = callback.getProductDetails().size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        val item = callback.getProductDetails()[position]

        when (holder) {
            is TitleViewHolder -> holder.bindItem(item as TitleRecyclerItem)
            is LabelValueViewHolder -> holder.bindItem(item as LabelValueRecyclerItem)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemViewType(position: Int) = callback.getProductDetails()[position].viewType

    interface Callback {
        fun getProductDetails(): List<RecyclerItem>
    }

}