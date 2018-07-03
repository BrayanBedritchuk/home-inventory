package br.com.sailboat.homeinventory.domain.usecase.product

import br.com.sailboat.homeinventory.domain.repository.ProductRepository
import br.com.sailboat.homeinventory.domain.usecase.UseCase
import javax.inject.Inject

class DeleteProduct @Inject constructor(private val productRepository: ProductRepository) : UseCase<Long, Unit>() {

    override fun execute(productId: Long) {
        val product = productRepository.getProduct(productId)
        productRepository.remove(product)
    }

}