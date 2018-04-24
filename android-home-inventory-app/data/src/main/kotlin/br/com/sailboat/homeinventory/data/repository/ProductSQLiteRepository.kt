package br.com.sailboat.homeinventory.data.repository

import br.com.sailboat.homeinventory.core.Filter
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.repository.ProductRepository
import br.com.sailboat.homeinventory.data.mapper.ProductDataMapper

class ProductSQLiteRepository(val productSqlite: ProductSQLite) : ProductRepository {

    private val productDataMapper = ProductDataMapper()

    override fun save(product: Product) {
        val productData = productDataMapper.transform(product)
        if (productData.id == null || productData.id == -1L) {
            productSqlite.insert(productData)
        } else {
            productSqlite.update(productData)
        }
    }

    override fun remove(product: Product) {
        productSqlite.delete(product.id)
    }

    override fun getAll(filter: Filter): List<Product> {
        val productsData = productSqlite.getAllProducts(filter)
        return productDataMapper.transform(productsData)
    }

    override fun findById(productId: Long): Product {
        val productData = productSqlite.getProductById(productId)
        return ProductDataMapper().transform(productData)
    }

}