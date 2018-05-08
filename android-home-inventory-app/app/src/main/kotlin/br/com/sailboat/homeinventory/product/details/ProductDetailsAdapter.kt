package br.com.sailboat.homeinventory.view.product.details

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import br.com.sailboat.homeinventory.helper.ViewType
import br.com.sailboat.homeinventory.model.LabelAndValueModel
import br.com.sailboat.homeinventory.model.RecyclerViewItem
import br.com.sailboat.homeinventory.model.TitleModel
import br.com.sailboat.homeinventory.model.viewholder.LabelAndValueViewHolder
import br.com.sailboat.homeinventory.model.viewholder.TitleViewHolder


class ProductDetailsAdapter(var callback: Callback) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            ViewType.TITLE.ordinal -> return TitleViewHolder(
                parent
            )
            ViewType.LABEL_VALUE.ordinal -> return LabelAndValueViewHolder(
                parent
            )
            else -> throw RuntimeException("ViewHolder not found")
        }

    }

    override fun getItemCount() = callback.getProductDetails().size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = callback.getProductDetails()[position]

        when (holder) {
            is TitleViewHolder -> holder.bindItem(item as TitleModel)
            is LabelAndValueViewHolder -> holder.bindItem(item as LabelAndValueModel)
            else -> throw RuntimeException("ViewHolder not found")
        }
    }

    override fun getItemViewType(position: Int) = callback.getProductDetails()[position].viewType

    interface Callback {
        fun getProductDetails(): List<RecyclerViewItem>
    }

}