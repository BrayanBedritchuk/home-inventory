package br.com.sailboat.homeinventory.data.repository

import android.app.Application
import android.arch.paging.DataSource
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.data.AppDatabase
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.data.ProductDataMapper
import br.com.sailboat.homeinventory.data.dao.ProductDAO

class ProductRoomRepository(application: Application) {

    var productDAO: ProductDAO
    val mapper = ProductDataMapper()

    init {
        val db: AppDatabase =
            AppDatabase.getInstance(application)
        productDAO = db.productDao()
    }

    fun getAllProducts(): DataSource.Factory<Int, ProductData> {
        return productDAO.getAll()
    }

    fun save(product: ProductData) {
        productDAO.insert(product)
    }

    fun remove(product: Product) {
        productDAO.delete(mapper.transform(product))
    }


}