package br.com.sailboat.homeinventory.ui.model

data class ProductView(
    var id: Long = -1,
    val name: String,
    var quantity: Int = 0,
    override val viewType: Int = ViewType.PRODUCT.ordinal
) : RecyclerViewItem