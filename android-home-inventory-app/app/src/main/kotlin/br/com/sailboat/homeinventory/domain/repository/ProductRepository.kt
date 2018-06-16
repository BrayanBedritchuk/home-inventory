package br.com.sailboat.homeinventory.domain.repository

import br.com.sailboat.homeinventory.domain.Either
import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.failure.Failure


interface ProductRepository {

    fun getProduct(productId: Long): Product
    fun getProducts(): Either<Failure, List<Product>>
    fun getProductList(): List<Product>
    fun insert(product: Product)
    fun update(product: Product)
    fun remove(product: Product)

}