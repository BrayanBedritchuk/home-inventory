package br.com.sailboat.homeinventory.view.product.list

import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.model.Product

class ProductListViewModel {

    val products = ArrayList<Product>()
    var filter = ProductFilter()

}