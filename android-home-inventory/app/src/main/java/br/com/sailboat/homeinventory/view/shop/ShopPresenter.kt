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
        view.showProductDetails(product.id)
    }

    interface View {
        fun showProductDetails(productId: Long)
    }

}