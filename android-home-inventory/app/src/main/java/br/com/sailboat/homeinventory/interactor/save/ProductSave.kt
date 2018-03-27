package br.com.sailboat.homeinventory.interactor.save

import android.content.Context
import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.homeinventory.dao.ProductDao
import br.com.sailboat.homeinventory.dao.ProductQuantityDao
import br.com.sailboat.homeinventory.model.Product

class ProductSave(val context: Context) {

    fun save(product: Product) {
        saveProduct(product)
        saveQuantity(product)
    }

    private fun saveProduct(product: Product) {
        if (product.id == EntityHelper.NO_ID) {
            ProductDao.newInstance(context).insert(product)
        } else {
            ProductDao.newInstance(context).update(product)
        }
    }

    private fun saveQuantity(product: Product) {
        val dao = ProductQuantityDao.newInstance(context)

        val currentQuantity = dao.getCurrentQuantity(product.id)

        if (product.quantity != currentQuantity) {
            dao.insert(product.id, product.quantity)
        }
    }
}