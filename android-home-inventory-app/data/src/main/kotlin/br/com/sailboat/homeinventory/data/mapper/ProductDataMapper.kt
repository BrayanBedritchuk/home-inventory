package br.com.sailboat.homeinventory.data.mapper

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.data.model.ProductData

class ProductDataMapper {

    fun transform(productData: ProductData): Product {
        val product = Product()
        product.id = productData.id
        product.name = productData.name
        product.quantity = productData.quantity

        return product
    }

    fun transform(productsData: List<ProductData>): List<Product> {
        val products = mutableListOf<Product>()

        productsData.forEach {
            products.add(transform(it))
        }

        return products
    }

    fun transform(product: Product) = ProductData(product.id, product.name, product.quantity)

}