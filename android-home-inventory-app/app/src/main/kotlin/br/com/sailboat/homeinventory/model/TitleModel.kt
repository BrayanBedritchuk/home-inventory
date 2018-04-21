package br.com.sailboat.homeinventory.presentation.model

import br.com.sailboat.homeinventory.presentation.helper.ViewType

data class TitleModel(
    override val viewType: Int = ViewType.TITLE.ordinal,
    val title: String
) : RecyclerViewItem