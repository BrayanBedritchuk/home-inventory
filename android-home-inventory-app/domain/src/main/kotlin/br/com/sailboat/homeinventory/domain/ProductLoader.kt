package br.com.sailboat.homeinventory.domain

import br.com.sailboat.homeinventory.core.Filter
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.repository.ProductRepository

class ProductLoader(val productRepository: ProductRepository) {

    fun loadProducts(filter: Filter): List<Product> {
        val products = productRepository.getAll(filter)
        products.forEach { it.quantity = loadQuantity(it.id) }
        return products
    }

    fun loadProduct(productId: Long): Product {
        val product = productRepository.findById(productId)
        product.quantity = loadQuantity(productId)
        return product
    }

    fun loadQuantity(productId: Long): Int {
//        return productRepository.getCurrentQuantity(productId)
        return 100
    }

}