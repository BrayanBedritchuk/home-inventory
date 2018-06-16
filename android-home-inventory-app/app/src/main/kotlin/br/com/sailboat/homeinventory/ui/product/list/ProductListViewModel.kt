package br.com.sailboat.homeinventory.ui.product.list

import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.ui.model.ProductView
import javax.inject.Inject

class ProductListViewModel @Inject constructor() {

    var products = mutableListOf<ProductView>()
    var filter = ProductFilter()

}