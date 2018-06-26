package br.com.sailboat.homeinventory.domain.usecase.product

import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.repository.ProductRepository
import br.com.sailboat.homeinventory.domain.usecase.UseCase
import javax.inject.Inject

class GetProducts @Inject constructor(private val productRepository: ProductRepository) : UseCase<None, List<Product>>() {

    override fun execute(params: None) = productRepository.getProductList()

}