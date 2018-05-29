package br.com.sailboat.homeinventory.ui.product.list

import android.arch.lifecycle.MutableLiveData
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.domain.usecase.GetProducts
import br.com.sailboat.homeinventory.domain.usecase.UseCase
import br.com.sailboat.homeinventory.ui.base.BaseViewModel
import br.com.sailboat.homeinventory.ui.model.ProductView
import javax.inject.Inject

class ProductListViewModel @Inject constructor(private val getProducts: GetProducts) : BaseViewModel() {

    var products: MutableLiveData<List<ProductView>> = MutableLiveData()
    var filter = ProductFilter()


    override fun init() {
        getProducts.execute(UseCase.None()) { it.either(::handleFailure, ::handleProductList) }
    }

    private fun handleProductList(products: List<Product>) {
        this.products.value = products.map { ProductView(it.id, it.name, it.quantity) }
    }

}