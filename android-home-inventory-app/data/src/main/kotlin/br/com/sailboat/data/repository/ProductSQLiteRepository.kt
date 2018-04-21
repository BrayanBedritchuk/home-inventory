package br.com.sailboat.homeinventory.data.repository

import br.com.sailboat.homeinventory.core.Filter
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.repository.ProductRepository
import br.com.sailboat.homeinventory.data.mapper.ProducDataMapper

// Inject the productSqlite
class ProductSQLiteRepository(val produtSqlite: ProductSQLite) : ProductRepository {

    override fun add(product: Product) {
        val productData = ProducDataMapper().transform(product)

        // TODO: CHECK AND INSERT THE QUANTITY
        produtSqlite.insert(productData)
    }

    override fun edit(product: Product) {
        val productData = ProducDataMapper().transform(product)

        // TODO: CHECK AND UPDATE THE QUANTITY
        produtSqlite.update(productData)
    }

    override fun remove(product: Product) {
        produtSqlite.delete(product.id)
    }

    override fun getAll(filter: Filter): MutableList<Product> {
        val productsData = produtSqlite.getAllProducts(filter)
        val products = ArrayList<Product>()

        productsData.forEach {
            val product = ProducDataMapper().transform(it)
            // TODO: LOAD THE QUANTITY
            products.add(product)
        }

        return products
    }

    override fun findById(productId: Long): Product {
        val productData = produtSqlite.getProductById(productId)
        return ProducDataMapper().transform(productData)
    }

}