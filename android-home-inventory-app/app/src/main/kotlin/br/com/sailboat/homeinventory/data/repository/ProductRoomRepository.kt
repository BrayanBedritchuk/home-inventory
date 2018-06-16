package br.com.sailboat.homeinventory.data.repository

import android.arch.paging.DataSource
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.data.ProductDataMapper
import br.com.sailboat.homeinventory.data.dao.ProductDAO
import br.com.sailboat.homeinventory.domain.Either
import br.com.sailboat.homeinventory.domain.entity.Product
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

    override fun getProductList(): List<Product> {
        val products = productDAO.getProducts()
        return mapper.transform(products)
    }

    override fun getProduct(productId: Long): Product {
        val product = productDAO.getProduct(productId)
        return mapper.transform(product)
    }

    override fun remove(product: Product) {
        productDAO.delete(mapper.transform(product))
    }

    override fun insert(product: Product) {
        productDAO.insert(mapper.transform(product))
    }

    override fun update(product: Product) {
        productDAO.update(mapper.transform(product))
    }


}