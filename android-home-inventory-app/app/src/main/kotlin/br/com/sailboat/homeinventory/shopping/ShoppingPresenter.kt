package br.com.sailboat.homeinventory.presentation.shopping

import android.os.Bundle
import android.util.Log
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.interactor.UseCaseWithResponse
import br.com.sailboat.homeinventory.data.repository.SQLiteRepositoryFactory
import br.com.sailboat.domain.GetProductsAsync
import br.com.sailboat.homeinventory.presentation.model.ProductModel
import br.com.sailboat.homeinventory.presentation.product.ProductModelMapper
import br.com.sailboat.homeinventory.view.shopping.ShoppingViewModel

class ShoppingPresenter(
    private val view: View,
    private val viewModel: ShoppingViewModel
) {

    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            loadProducts()
        }
    }

    private fun loadProducts() {
        GetProductsAsync(
            SQLiteRepositoryFactory(viewModel.getApplication()).productRepository,
            viewModel.productFilter
        ).execute(
            object : UseCaseWithResponse.Response<List<Product>> {
                override fun onSuccess(loadedProducts: List<Product>) {
                    if (view != null) {
                        viewModel.products.postValue(ProductModelMapper().transform(loadedProducts))
                    }
                }

                override fun onFail(exception: Exception) {
                    Log.e("ERROR", "Error", exception);
                }
            })
    }

    fun onClickShoppingProduct(position: Int) {
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

    fun onClickOkProductDialog(productId: Long, quantity: Int?) {
        if (viewModel.shoppingCart.containsKey(productId)) {
            viewModel.shoppingCart.remove(productId)
        }

        if (quantity != null && quantity > 0) {
            viewModel.shoppingCart.put(productId, quantity)
        }

        view.refreshShoppingList()
    }

    fun onClickMenuSave() {
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
        fun refreshShoppingList()
    }


}