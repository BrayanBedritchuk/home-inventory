package br.com.sailboat.homeinventory.data.model

import java.math.BigDecimal

data class ShoppingProductData(
    val shoppingId: Long,
    val productId: Long,
    val quantity: Int,
    var unitPrice: BigDecimal = BigDecimal.ZERO
)