package br.com.sailboat.homeinventory.domain

import android.content.Context
import br.com.sailboat.homeinventory.core.repository.ProductRepository

class ShoppingSaver(val context: Context, val productRepository: ProductRepository) {

    // TODO: REFACTOR
//    fun createAndSaveShopping(establishmentId: Long, shoppingCart: HashMap<Long, Int>) {
//
//        val shoppingDao = ShoppingRepository.newInstance(context)
//
//        try {
//            shoppingDao.beginTransactionNonExclusive()
//
//            val shopping = ShoppingData(
//                establishmentId = establishmentId,
//                dateTime = Calendar.getInstance()
//            )
//
//            saveShopping(shopping)
//
//            shoppingCart.keys.forEach {
//                val product = ProductLoader(productRepository)
//                    .loadProduct(it)
//                val quantity = shoppingCart.get(it)!!
//
//                product.quantity += quantity
//
//                val shoppingProduct =
//                    ShoppingProductData(
//                        shoppingId = shopping.id,
//                        productId = product.id,
//                        quantity = quantity
//                    )
//
//                ShoppingProductRepository.newInstance(context).insert(shoppingProduct)
//
//                ProductSaver(context).save(product)
//            }
//
//            shoppingDao.setTransactionSuccessful()
//
//        } finally {
//            shoppingDao.endTransaction()
//        }
//
//    }
//
//    private fun saveShopping(shopping: ShoppingData) {
//        if (shopping.id == EntityHelper.NO_ID) {
//            ShoppingRepository.newInstance(context).insert(shopping)
//        } else {
//            ShoppingRepository.newInstance(context).update(shopping)
//        }
//    }

}