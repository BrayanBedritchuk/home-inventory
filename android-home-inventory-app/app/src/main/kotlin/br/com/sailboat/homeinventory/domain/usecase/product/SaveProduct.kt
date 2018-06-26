package br.com.sailboat.homeinventory.domain.usecase.product

import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.entity.EntityHelper
import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.repository.ProductRepository
import br.com.sailboat.homeinventory.domain.usecase.UseCase
import javax.inject.Inject

class SaveProduct @Inject constructor(private val productRepository: ProductRepository) : UseCase<Product, None>() {

    override fun execute(product: Product): None {
        if (product.id == EntityHelper.NO_ID) {
            productRepository.insert(product)
        } else {
            productRepository.update(product)
        }

        return None()
    }

}