package br.com.sailboat.homeinventory.presentation.product

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.presentation.model.ProductModel

class ProductModelMapper {

    fun transform(products: List<Product>): List<ProductModel> {
        val productsModel = ArrayList<ProductModel>()

        products.forEach {
            productsModel.add(transform(it))
        }

        return productsModel
    }

    fun transform(product: Product): ProductModel {
        return ProductModel(
            product.id,
            product.name,
            product.quantity
        )
    }

}