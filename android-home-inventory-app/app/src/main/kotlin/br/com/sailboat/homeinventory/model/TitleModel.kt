package br.com.sailboat.homeinventory.model

import br.com.sailboat.homeinventory.helper.ViewType

data class TitleModel(
    override val viewType: Int = ViewType.TITLE.ordinal,
    val title: String
) : RecyclerViewItem