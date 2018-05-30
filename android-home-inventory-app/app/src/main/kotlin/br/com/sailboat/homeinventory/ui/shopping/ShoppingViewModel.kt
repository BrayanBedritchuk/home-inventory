package br.com.sailboat.homeinventory.ui.shopping

import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PagedList
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.helper.Event
import br.com.sailboat.homeinventory.ui.base.BaseViewModel

// TODO: INJECT
class ShoppingViewModel() : BaseViewModel() {

    // TODO: INJECT
//    val productRepository: ProductRoomRepository =
//        ProductRoomRepository(application)
    val name = MutableLiveData<String>()
    val products = MutableLiveData<PagedList<ProductData>>()
    val shoppingCart = HashMap<Long, Int>()
    val productFilter = ProductFilter()

    val errorMessage = MutableLiveData<Event<String>>()
    val showShoppingProduct = MutableLiveData<Event<Long>>()
    val updateShoppingList = MutableLiveData<Event<String>>()
    val successOnSaveShopping = MutableLiveData<Event<String>>()

    init {
//        val config = PagedList.Config.Builder()
//            .setPageSize(10)
//            .setInitialLoadSizeHint(20)
//            .setPrefetchDistance(10)
//            .setEnablePlaceholders(false)
//            .build()
//
//        val factory = productRepository.getAllProducts()
//        products = LivePagedListBuilder(factory, config).build()
    }


}