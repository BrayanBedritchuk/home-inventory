package br.com.sailboat.homeinventory.ui.base

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseViewHolder<in T>(itemView: View) : RecyclerView.ViewHolder(itemView) {

    abstract fun bindItem(item: T)

    companion object {
        fun inflate(parent: ViewGroup, layoutId: Int) : View {
            return LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        }
    }

}