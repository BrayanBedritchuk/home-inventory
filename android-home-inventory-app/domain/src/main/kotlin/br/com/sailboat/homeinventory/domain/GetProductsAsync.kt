package br.com.sailboat.homeinventory.domain

import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.homeinventory.core.Filter
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.interactor.GetProducts
import br.com.sailboat.homeinventory.core.interactor.UseCaseWithResponse
import br.com.sailboat.homeinventory.core.repository.ProductRepository


class GetProductsAsync(val productRepository: ProductRepository, val filter: Filter) :
    UseCaseWithResponse<List<Product>> {

    private lateinit var products: List<Product>

    override fun execute(response: UseCaseWithResponse.Response<List<Product>>) {
        AsyncHelper.execute(object : AsyncHelper.Callback {

            @Throws(Exception::class)
            override fun doInBackground() {

                GetProducts(productRepository, filter).execute(object :
                    UseCaseWithResponse.Response<List<Product>> {

                    override fun onSuccess(response: List<Product>) {
                        products = response
                    }

                    override fun onFail(exception: Exception) {
                        throw exception
                    }
                })

            }

            override fun onSuccess() {
                response.onSuccess(products)
            }

            override fun onFail(e: Exception) {
                response.onFail(e)
            }

        })
    }

}