package br.com.sailboat.homeinventory.view.shopping

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.product.ProductModel

class ShoppingViewModel(application: Application) : AndroidViewModel(application) {

    var firstSession = true
    val name = MutableLiveData<String>()
    val products = MutableLiveData<List<ProductModel>>()
    val shoppingCart = HashMap<Long, Int>()
    val productFilter = ProductFilter()

    fun getProducts(): List<ProductModel> {
        return if (products.value != null) {
            products.value!!
        } else {
            mutableListOf()
        }
    }

}