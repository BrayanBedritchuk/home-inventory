package br.com.sailboat.domain

import android.content.Context

class ProductSaver(val context: Context) {

//    fun save(product: Product) {
//        saveProduct(product)
//        saveQuantity(product)
//    }
//
//    private fun saveProduct(product: Product) {
//        if (product.id == EntityHelper.NO_ID) {
//            ProductRepositorySqlite.newInstance(context).insert(product)
//        } else {
//            ProductRepositorySqlite.newInstance(context).update(product)
//        }
//    }
//
//    private fun saveQuantity(product: Product) {
//        val dao = ProductQuantityRepository.newInstance(context)
//
//        val currentQuantity = dao.getCurrentQuantity(product.id)
//
//        if (product.quantity != currentQuantity) {
//            dao.insert(product.id, product.quantity)
//        }
//    }
}