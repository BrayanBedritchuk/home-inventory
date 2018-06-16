package br.com.sailboat.homeinventory.data

import br.com.sailboat.homeinventory.domain.entity.Product


class ProductDataMapper {

    fun transform(productData: ProductData): Product {
        return Product(productData.id, productData.name, productData.quantity)
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