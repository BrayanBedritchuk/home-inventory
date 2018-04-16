package br.com.sailboat.homeinventory.model

import java.math.BigDecimal

data class ShoppingProduct(
    val shoppingId: Long,
    val productId: Long,
    val quantity: Int,
    var unitPrice: BigDecimal = BigDecimal.ZERO
)