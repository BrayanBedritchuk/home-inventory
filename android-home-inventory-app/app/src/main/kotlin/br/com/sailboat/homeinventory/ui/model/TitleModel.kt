package br.com.sailboat.homeinventory.ui.model

data class TitleModel(
    override val viewType: Int = ViewType.TITLE.ordinal,
    val title: String
) : RecyclerViewItem