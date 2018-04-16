package br.com.sailboat.homeinventory.view.shopping

import android.os.Bundle
import br.com.sailboat.homeinventory.interactor.save.ShoppingSave
import br.com.sailboat.homeinventory.model.Product

class ShoppingPresenter(private val view: ShoppingPresenter.View, private val viewModel: ShoppingViewModel) {

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.loadProducts()
        } else {
            onCreateAfterConfigurationChanges()
        }
    }

    private fun onCreateAfterConfigurationChanges() {

    }

    fun onClickShoppingProduct(position: Int) {
        val product = viewModel.getProducts()[position]

        val quantity: Int = if (viewModel.shoppingCart.containsKey(product.id)) {
            viewModel.shoppingCart.get(product.id)!!
        } else {
            0
        }

        view.showShopProduct(product, quantity)
    }

    fun wasPurchased(productId: Long) = viewModel.shoppingCart.containsKey(productId)

    fun getShoppingQuantity(productId: Long) = viewModel.shoppingCart[productId].toString()

    fun onClickOkProductDialog(productId: Long, quantity: Int?) {
        if (viewModel.shoppingCart.containsKey(productId)) {
            viewModel.shoppingCart.remove(productId)
        }

        if (quantity != null && quantity > 0) {
            viewModel.shoppingCart.put(productId, quantity)
        }

        view.updateRecycler()
    }

    fun onClickMenuSave() {
//        viewModel.shoppingCart.keys.forEach {
//            val product = ProductLoader(viewModel.getApplication()).loadProduct(it)
//            product.quantity = viewModel.shoppingCart.get(it)!!
//
//            ProductSave(viewModel.getApplication()).createAndSaveShopping(product)
//        }
        // validate first
        ShoppingSave(viewModel.getApplication()).createAndSaveShopping(-1, viewModel.shoppingCart)
        view.finishActivity()
    }

    interface View {
        fun showShopProduct(productId: Product, quantity: Int)
        fun finishActivity()
        fun updateRecycler()
    }


}