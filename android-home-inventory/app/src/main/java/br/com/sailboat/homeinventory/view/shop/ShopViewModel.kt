package br.com.sailboat.homeinventory.view.shop

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel


class ShopViewModel : ViewModel() {

    val name = MutableLiveData<String>()
    val products = MutableLiveData<String>()

    fun loadProducts() {

    }

}