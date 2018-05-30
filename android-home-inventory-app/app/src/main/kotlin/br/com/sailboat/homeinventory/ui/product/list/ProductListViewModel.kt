package br.com.sailboat.homeinventory.ui.product.list

import android.arch.lifecycle.MutableLiveData
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.ui.base.BaseViewModel
import br.com.sailboat.homeinventory.ui.model.ProductView

class ProductListViewModel : BaseViewModel() {

    var products: MutableLiveData<List<ProductView>> = MutableLiveData()
    var filter = ProductFilter()

}