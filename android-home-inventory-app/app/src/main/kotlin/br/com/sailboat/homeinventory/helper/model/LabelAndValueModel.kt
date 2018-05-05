package br.com.sailboat.homeinventory.helper.model

import br.com.sailboat.homeinventory.helper.ViewType

data class LabelAndValueModel(
    override val viewType: Int = ViewType.LABEL_VALUE.ordinal,
    val label: String,
    val value: String
) : RecyclerViewItem