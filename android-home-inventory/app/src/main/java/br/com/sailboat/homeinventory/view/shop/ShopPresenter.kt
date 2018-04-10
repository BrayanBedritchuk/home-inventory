package br.com.sailboat.homeinventory.view.shop

import android.os.Bundle

class ShopPresenter(private val view: ShopPresenter.View, private val viewModel: ShopViewModel) {

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            viewModel.loadProducts()
        } else {
            onCreateAfterConfigurationChanges()
        }
    }

    private fun onCreateAfterConfigurationChanges() {

    }

    fun onClickProduct(position: Int) {
        val product = viewModel.getProducts()[position]
        view.showShopProduct(product.id, 0)
    }

    interface View {
        fun showShopProduct(productId: Long, quantity: Int)
    }

}