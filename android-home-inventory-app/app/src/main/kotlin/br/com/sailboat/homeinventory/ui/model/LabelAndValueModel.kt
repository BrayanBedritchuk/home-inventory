package br.com.sailboat.homeinventory.ui.model

data class LabelAndValueModel(
    override val viewType: Int = ViewType.LABEL_VALUE.ordinal,
    val label: String,
    val value: String
) : RecyclerViewItem