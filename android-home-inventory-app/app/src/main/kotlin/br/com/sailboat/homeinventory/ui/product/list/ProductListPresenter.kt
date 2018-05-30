package br.com.sailboat.homeinventory.ui.product.list

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.failure.Failure
import br.com.sailboat.homeinventory.domain.usecase.GetProducts
import br.com.sailboat.homeinventory.helper.Event
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import br.com.sailboat.homeinventory.ui.model.ProductView
import javax.inject.Inject

class ProductListPresenter @Inject constructor(private val getProducts: GetProducts) : BasePresenter() {

    lateinit var viewModel: ProductListViewModel
    lateinit var events: ProductListEvents

    override fun create() {
        loadProducts()
    }

    override fun restart() {
        viewModel.products.value = viewModel.products.value
    }

    private fun loadProducts() {
        viewModel.notifyStartAsync()
        getProducts.execute({ it.either(::handleFailure, ::handleProductList) }, None())
    }

    private fun handleFailure(failure: Failure) {
        viewModel.notifyFinishAsync()
        viewModel.notifyError("Não foi possível carregar a lista")
    }

    private fun handleProductList(products: List<Product>) {
        viewModel.notifyFinishAsync()
        viewModel.products.value = products.map { ProductView(it.id, it.name, it.quantity) }
    }

    fun onClickProduct(position: Int) {
        viewModel.products.value?.let {
            val product = it.get(position)
            events.productDetails.value = Event(product.id)
        }
    }

}