package br.com.sailboat.homeinventory.presentation.shopping

import android.util.Log
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.homeinventory.core.Logger
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.interactor.product.GetProducts
import br.com.sailboat.homeinventory.core.interactor.UseCaseWithResponse
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.presentation.model.ProductModel
import br.com.sailboat.homeinventory.presentation.product.ProductModelMapper
import br.com.sailboat.homeinventory.view.shopping.ShoppingViewModel

class ShoppingPresenter(
    private val view: ShoppingPresenter.View,
    private val viewModel: ShoppingViewModel,
    private val repositoryFactory: RepositoryFactory,
    private val logger: Logger
) {

    private val TAG = "ShoppingPresenter"

    fun onCreate() {
        logger.debug(TAG, "onCreate")
        if (viewModel.firstSession) {
            loadProducts()
        }
        viewModel.firstSession = false
    }

    private fun loadProducts() {
        logger.debug(TAG, "loadProducts")
        AsyncHelper.execute(object : AsyncHelper.Callback {

            lateinit var products: List<Product>

            @Throws(Exception::class)
            override fun doInBackground() {

                GetProducts(
                    repositoryFactory.productRepository,
                    viewModel.productFilter
                ).execute(
                    object : UseCaseWithResponse.Response<List<Product>> {

                        override fun onSuccess(responseProducts: List<Product>) {
                            products = responseProducts
                        }

                        override fun onFail(exception: Exception) {
                            throw exception
                        }
                    })

            }

            override fun onSuccess() {
                viewModel.products.postValue(ProductModelMapper().transform(products))
            }

            override fun onFail(e: Exception) {
                Log.e("ERROR", "Error", e);
            }

        })
    }

    fun onClickShoppingProduct(position: Int) {
        logger.debug(TAG, "onClickShoppingProduct")
        val product = viewModel.getProducts()[position]

        val quantity: Int = if (viewModel.shoppingCart.containsKey(product.id)) {
            viewModel.shoppingCart.get(product.id)!!
        } else {
            0
        }

        view.showShoppingProduct(product, quantity)
    }

    fun wasPurchased(productId: Long) = viewModel.shoppingCart.containsKey(productId)

    fun getShoppingQuantity(productId: Long) = viewModel.shoppingCart[productId].toString()

    fun onAddProduct(productId: Long, quantity: Int?) {
        logger.debug(TAG, "onAddProduct productId: $productId, quantity: $quantity")
        if (viewModel.shoppingCart.containsKey(productId)) {
            viewModel.shoppingCart.remove(productId)
        }

        if (quantity != null && quantity > 0) {
            viewModel.shoppingCart.put(productId, quantity)
        }

        view.updateShoppingList()
    }

    fun onClickCheckout() {
//        viewModel.shoppingCart.keys.forEach {
//            val product = ProductLoader(viewModel.getApplication()).loadProduct(it)
//            product.quantity = viewModel.shoppingCart.get(it)!!
//
//            ProductSaver(viewModel.getApplication()).createAndSaveShopping(product)
//        }
//        // validate first
//        ShoppingSaver(viewModel.getApplication())
//            .createAndSaveShopping(-1, viewModel.shoppingCart)
        view.finishWithSuccess()
    }

    interface View {
        fun showShoppingProduct(productId: ProductModel, quantity: Int)
        fun finishWithSuccess()
        fun updateShoppingList()
    }


}