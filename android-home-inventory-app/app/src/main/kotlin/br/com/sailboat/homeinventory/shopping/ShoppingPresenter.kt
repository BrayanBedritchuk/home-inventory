package br.com.sailboat.homeinventory.presentation.shopping

import android.util.Log
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.homeinventory.core.Logger
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.interactor.UseCaseWithResponse
import br.com.sailboat.homeinventory.core.interactor.product.GetProducts
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.helper.base.BasePresenter
import br.com.sailboat.homeinventory.model.mapper.ProductModelMapper
import br.com.sailboat.homeinventory.model.ProductModel
import br.com.sailboat.homeinventory.view.shopping.ShoppingViewModel
import javax.inject.Inject

class ShoppingPresenter @Inject constructor(
    private val repositoryFactory: RepositoryFactory,
    private val logger: Logger
) : BasePresenter() {

    private val TAG = "ShoppingPresenter"

    lateinit var view: ShoppingPresenter.View
    lateinit var viewModel: ShoppingViewModel

    override fun onCreateView() {
        logger.debug(TAG, "onCreate")
        loadProducts()
    }

    override fun onRestartView() {
        logger.debug(TAG, "onRestart")
        view.updateShoppingList()
    }

    private fun loadProducts() {
        logger.debug(TAG, "loadProducts")
        view.showProgress()
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
                viewModel.products.value = (ProductModelMapper().transform(products))
                view?.updateShoppingList()
                view?.hideProgress()
            }

            override fun onFail(e: Exception) {
                Log.e("ERROR", "Error", e);
                view?.hideProgress()
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

        view?.showShoppingProduct(product, quantity)
    }

    fun getProducts() = viewModel.getProducts()

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

        view?.updateShoppingList()
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
        view?.finishWithSuccess()
    }

    interface View : BasePresenter.View {
        fun showShoppingProduct(productId: ProductModel, quantity: Int)
        fun finishWithSuccess()
        fun updateShoppingList()
    }


}