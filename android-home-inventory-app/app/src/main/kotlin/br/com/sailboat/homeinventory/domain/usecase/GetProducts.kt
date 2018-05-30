package br.com.sailboat.homeinventory.domain.usecase

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.repository.ProductRepository
import javax.inject.Inject

class GetProducts @Inject constructor(private val productRepository: ProductRepository) :
    UseCase<List<Product>, None>() {

    override suspend fun run(params: None) = productRepository.getProducts()

}