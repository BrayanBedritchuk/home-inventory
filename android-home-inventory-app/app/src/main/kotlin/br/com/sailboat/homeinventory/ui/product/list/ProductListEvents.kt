package br.com.sailboat.homeinventory.ui.product.list

import android.arch.lifecycle.MutableLiveData
import br.com.sailboat.homeinventory.helper.Event

class ProductListEvents {

    val productDetails = MutableLiveData<Event<Long>>()

    fun notifyProductDetails(productId: Long) {
        productDetails.value = Event(productId)
    }

}