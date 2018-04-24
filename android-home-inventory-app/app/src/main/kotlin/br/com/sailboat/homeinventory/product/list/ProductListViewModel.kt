package br.com.sailboat.homeinventory.view.product.list

import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.dao.filter.ProductFilter

class ProductListViewModel {

    val products = mutableListOf<Product>()
    var filter = ProductFilter()

}