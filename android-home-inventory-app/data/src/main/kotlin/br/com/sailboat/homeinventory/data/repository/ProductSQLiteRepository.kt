package br.com.sailboat.homeinventory.data.repository

import br.com.sailboat.homeinventory.core.Filter
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.repository.ProductRepository
import br.com.sailboat.homeinventory.data.mapper.ProducDataMapper

// Inject the productSqlite
class ProductSQLiteRepository(val productSqlite: ProductSQLite) : ProductRepository {

    override fun add(product: Product) {
        val productData = ProducDataMapper().transform(product)

        // TODO: CHECK AND INSERT THE QUANTITY
        productSqlite.insert(productData)
    }

    override fun edit(product: Product) {
        val productData = ProducDataMapper().transform(product)

        // TODO: CHECK AND UPDATE THE QUANTITY
        productSqlite.update(productData)
    }

    override fun remove(product: Product) {
        productSqlite.delete(product.id)
    }

    override fun getAll(filter: Filter): List<Product> {
        val productsData = productSqlite.getAllProducts(filter)
        val products = ArrayList<Product>()

        productsData.forEach {
            val quantity = productSqlite.getCurrentQuantity(it.id)
            val product = ProducDataMapper().transform(it, quantity)
            products.add(product)
        }

        return products
    }

    override fun findById(productId: Long): Product {
        val productData = productSqlite.getProductById(productId)
        val quantity = productSqlite.getCurrentQuantity(productId)
        return ProducDataMapper().transform(productData, quantity)
    }

}