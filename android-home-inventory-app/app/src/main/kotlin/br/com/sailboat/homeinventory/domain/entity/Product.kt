package br.com.sailboat.homeinventory.domain.entity

data class Product(
    var id: Long = EntityHelper.NO_ID,
    val name: String,
    var quantity: Int = 0
)