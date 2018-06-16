package br.com.sailboat.homeinventory.ui.model

import br.com.sailboat.homeinventory.domain.entity.Product


class ProductViewMapper {

    fun transform(product: Product): ProductView {
        return ProductView(product.id, product.name, product.quantity)
    }

    fun transform(product: List<Product>): List<ProductView> {
        return product.map { transform(it) }
    }

}