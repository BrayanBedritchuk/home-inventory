package br.com.sailboat.homeinventory.interactor.loader

import android.content.Context
import br.com.sailboat.canoe.recycler.RecyclerItem
import br.com.sailboat.canoe.recycler.item.LabelValueRecyclerItem
import br.com.sailboat.canoe.recycler.item.TitleRecyclerItem
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.dao.ProductDao
import br.com.sailboat.homeinventory.dao.ProductQuantityDao
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.helper.ViewTypeHelper
import br.com.sailboat.homeinventory.model.Product

class ProductLoader(val context: Context) {

    fun loadProducts(filter: ProductFilter): List<Product> {
        val products = ProductDao.newInstance(context).getAllProducts(filter)

        products.forEach { it.quantity = loadQuantity(it.id) }

        return products
    }

    fun loadProductDetails(id: Long): List<RecyclerItem> {
        val product = loadProduct(id)

        val productDetails = ArrayList<RecyclerItem>()

        var title = TitleRecyclerItem(ViewTypeHelper.TITLE.ordinal)
        title.title = product.name

        var label = LabelValueRecyclerItem(ViewTypeHelper.LABEL_VALUE.ordinal)
        label.label = context.getString(R.string.label_quantity)
        label.value = product.quantity.toString()

        productDetails.add(title)
        productDetails.add(label)

        return productDetails
    }

    fun loadProduct(productId: Long): Product {
        val product = ProductDao.newInstance(context).getProductById(productId)
        product.quantity = loadQuantity(productId)

        return product
    }

    fun loadQuantity(productId: Long): Int {
        return ProductQuantityDao.newInstance(context).getCurrentQuantity(productId)
    }

}