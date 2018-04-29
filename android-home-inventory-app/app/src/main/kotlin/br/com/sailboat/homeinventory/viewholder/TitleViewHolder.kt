package br.com.sailboat.homeinventory.presentation.view_holder

import android.view.ViewGroup
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.model.TitleModel
import kotlinx.android.synthetic.main.vh_title.view.*

class TitleViewHolder(parent: ViewGroup) :
    BaseViewHolder<TitleModel>(inflate(parent, R.layout.vh_title)) {

    override fun bindItem(item: TitleModel) {
        itemView.tvTitle.text = item.title
    }

}