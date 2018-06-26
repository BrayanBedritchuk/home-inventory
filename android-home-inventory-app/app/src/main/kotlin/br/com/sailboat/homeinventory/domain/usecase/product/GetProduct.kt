package br.com.sailboat.homeinventory.domain.usecase.product

import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.repository.ProductRepository
import br.com.sailboat.homeinventory.domain.usecase.UseCase
import javax.inject.Inject

class GetProduct @Inject constructor(private val productRepository: ProductRepository) : UseCase<Long, Product>() {

    override fun execute(productId: Long) = productRepository.getProduct(productId)

}