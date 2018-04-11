package br.com.sailboat.homeinventory.view.shopping

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.interactor.loader.ProductLoader
import br.com.sailboat.homeinventory.model.Product
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers




class ShoppingViewModel(application: Application) : AndroidViewModel(application) {

    val name = MutableLiveData<String>()
    val products = MutableLiveData<List<Product>>()
    val shoppingCart = HashMap<Long, Int>()

    fun getProducts() : List<Product> {
        return if (products.value != null) {
            products.value!!
        } else {
            ArrayList()
        }
    }

    fun loadProducts() {
        Observable.fromCallable({
            products.postValue(ProductLoader(getApplication()).loadProducts(ProductFilter()))
        })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

}