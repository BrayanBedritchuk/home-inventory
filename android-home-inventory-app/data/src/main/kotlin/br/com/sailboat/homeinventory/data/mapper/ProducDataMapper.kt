package br.com.sailboat.homeinventory.data.mapper

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.data.model.ProductData

class ProducDataMapper {

    fun transform(productData: ProductData, quantity: Int): Product {
        val product = Product()
        product.id = productData.id
        product.name = productData.name
        product.quantity = quantity

        return product
    }

    fun transform(product: Product) = ProductData(product.id, product.name)

}