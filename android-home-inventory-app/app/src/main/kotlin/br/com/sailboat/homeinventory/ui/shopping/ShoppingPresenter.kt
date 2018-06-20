package br.com.sailboat.homeinventory.ui.shopping

import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.entity.Product
import br.com.sailboat.homeinventory.domain.usecase.GetProduct
import br.com.sailboat.homeinventory.domain.usecase.GetProducts
import br.com.sailboat.homeinventory.domain.usecase.SaveProduct
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import br.com.sailboat.homeinventory.ui.model.ProductView
import br.com.sailboat.homeinventory.ui.model.ProductViewMapper
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ShoppingPresenter @Inject constructor(
    private val viewModel: ShoppingViewModel,
    private val getProducts: GetProducts,
    private val getProduct: GetProduct,
    private val saveProduct: SaveProduct
) : BasePresenter<ShoppingContract.View>(), ShoppingContract.Presenter {

    override fun create() {
        loadProducts()
    }

    override fun restart() {
        updateShoppingItems()
    }

    override fun onClickProduct(position: Int) {
        val product = getShoppingItems()[position] as ProductView
        val quantity = viewModel.shoppingCart[product.id]
        view?.navigateToShoppingProduct(product, quantity ?: 0)
    }

    override fun onClickCheckout() {
        launch(UI) {
            try {
                view?.showProgress()
                async(CommonPool) {
                    viewModel.shoppingCart.keys.forEach {
                        val product = getProduct.execute(it)
                        product.quantity = viewModel.shoppingCart[it] ?: 0

                        saveProduct.execute(product)
                    }
                }.await()

                view?.closeWithSuccess()
            } catch (e: Exception) {
                view?.logError(e)
                view?.showErrorCheckout()
            } finally {
                view?.hideProgress()
            }
        }
    }

    override fun wasPurchased(productId: Long) = viewModel.shoppingCart.containsKey(productId)

    override fun getShoppingQuantity(productId: Long) = viewModel.shoppingCart[productId].toString()

    override fun onAddProduct(productId: Long, quantity: Int) {
        if (viewModel.shoppingCart.containsKey(productId)) {
            viewModel.shoppingCart.remove(productId)
        }

        if (quantity > 0) {
            viewModel.shoppingCart[productId] = quantity
        }

        view?.updateShoppingItems()
    }

    override fun getShoppingItems() = viewModel.shoppingItems

    private fun loadProducts() {
        launch(UI) {
            try {
                view?.showProgress()
                val products = async(CommonPool) { getProducts.execute(None()) }.await()
                updateProducts(products)
            } catch (e: Exception) {
                view?.logError(e)
                view?.showErrorLoadingProducts()
            } finally {
                view?.hideProgress()
            }
        }
    }

    private fun updateProducts(products: List<Product>) {
        viewModel.shoppingItems.clear()
        viewModel.shoppingItems.addAll(ProductViewMapper().transform(products))
        updateShoppingItems()
    }

    private fun updateShoppingItems() {
        view?.updateShoppingItems()

        if (viewModel.shoppingItems.isEmpty()) {
            view?.hideShoppingItems()
            view?.showEmptyView()
        } else {
            view?.showShoppingItems()
            view?.hideEmptyView()
        }
    }

}