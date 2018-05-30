package br.com.sailboat.homeinventory.domain.repository

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.domain.Either
import br.com.sailboat.homeinventory.domain.failure.Failure

interface ProductRepository {

    fun getProducts(): Either<Failure, List<Product>>
    fun getProductList(): List<Product>

}