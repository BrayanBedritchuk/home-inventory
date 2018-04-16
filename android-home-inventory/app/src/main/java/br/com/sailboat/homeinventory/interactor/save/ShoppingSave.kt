package br.com.sailboat.homeinventory.interactor.save

import android.content.Context
import br.com.sailboat.canoe.helper.EntityHelper
import br.com.sailboat.homeinventory.dao.ShoppingDao
import br.com.sailboat.homeinventory.dao.ShoppingProductDao
import br.com.sailboat.homeinventory.interactor.loader.ProductLoader
import br.com.sailboat.homeinventory.model.Shopping
import br.com.sailboat.homeinventory.model.ShoppingProduct
import java.util.*

class ShoppingSave(val context: Context) {

    // TODO: REFACTOR
    fun createAndSaveShopping(establishmentId: Long, shoppingCart: HashMap<Long, Int>) {

        val shoppingDao = ShoppingDao.newInstance(context)

        try {
            shoppingDao.beginTransactionNonExclusive()

            val shopping = Shopping(
                establishmentId = establishmentId,
                dateTime = Calendar.getInstance())

            saveShopping(shopping)

            shoppingCart.keys.forEach {
                val product = ProductLoader(context).loadProduct(it)
                val quantity = shoppingCart.get(it)!!

                product.quantity += quantity

                val shoppingProduct = ShoppingProduct(
                    shoppingId = shopping.id,
                    productId = product.id,
                    quantity = quantity
                )

                ShoppingProductDao.newInstance(context).insert(shoppingProduct)

                ProductSave(context).save(product)
            }

            shoppingDao.setTransactionSuccessful()

        } finally {
            shoppingDao.endTransaction()
        }

    }

    private fun saveShopping(shopping: Shopping) {
        if (shopping.id == EntityHelper.NO_ID) {
            ShoppingDao.newInstance(context).insert(shopping)
        } else {
            ShoppingDao.newInstance(context).update(shopping)
        }
    }

}