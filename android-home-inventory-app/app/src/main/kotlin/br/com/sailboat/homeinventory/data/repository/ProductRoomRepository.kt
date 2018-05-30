package br.com.sailboat.homeinventory.data.repository

import android.arch.paging.DataSource
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.data.ProductDataMapper
import br.com.sailboat.homeinventory.data.dao.ProductDAO
import br.com.sailboat.homeinventory.domain.Either
import br.com.sailboat.homeinventory.domain.failure.Failure
import br.com.sailboat.homeinventory.domain.failure.ProductFailure
import br.com.sailboat.homeinventory.domain.repository.ProductRepository

class ProductRoomRepository(var productDAO: ProductDAO) : ProductRepository {

    val mapper = ProductDataMapper()

    fun getAllProducts(): DataSource.Factory<Int, ProductData> {
        return productDAO.getAll()
    }

    override fun getProducts(): Either<Failure, List<Product>> {
        return try {
            val products = productDAO.getProducts()
            Either.Right(mapper.transform(products))
        } catch (e: Exception) {
            Either.Left(ProductFailure.ListNotAvailable())
        }
    }

    override fun getProductList() = mapper.transform(productDAO.getProducts())

    fun save(product: ProductData) {
        productDAO.insert(product)
    }

    fun remove(product: Product) {
        productDAO.delete(mapper.transform(product))
    }


}