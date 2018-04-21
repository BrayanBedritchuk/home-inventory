package br.com.sailboat.homeinventory.presentation.model

import br.com.sailboat.homeinventory.presentation.helper.ViewType

data class LabelAndValueModel(
    override val viewType: Int = ViewType.TITLE.ordinal,
    val label: String,
    val value: String
) : RecyclerViewItem