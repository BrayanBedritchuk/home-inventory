package br.com.sailboat.homeinventory.ui.shopping

import br.com.sailboat.homeinventory.R
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
) : BasePresenter<ShoppingPresenter.View>() {


    override fun create() {
        loadProducts()
    }

    override fun restart() {
        updateShoppingItems()
    }

    fun onClickProduct(position: Int) {
        val product = getShoppingItems()[position] as ProductView
        val quantity = viewModel.shoppingCart[product.id]
        view?.showShoppingProduct(product, quantity ?: 0)
    }

    fun onClickCheckout() {
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
                view?.showErrorMessage(R.string.msg_error)
            } finally {
                view?.hideProgress()
            }
        }
    }

    fun wasPurchased(productId: Long) = viewModel.shoppingCart.containsKey(productId)

    fun getShoppingQuantity(productId: Long) = viewModel.shoppingCart[productId].toString()

    fun onAddProduct(productId: Long, quantity: Int) {
        if (viewModel.shoppingCart.containsKey(productId)) {
            viewModel.shoppingCart.remove(productId)
        }

        if (quantity > 0) {
            viewModel.shoppingCart[productId] = quantity
        }

        view?.updateShoppingItems()
    }

    fun getShoppingItems() = viewModel.shoppingItems

    private fun loadProducts() {
        launch(UI) {
            try {
                view?.showProgress()
                val products = async(CommonPool) { getProducts.execute(None()) }.await()
                updateProducts(products)
            } catch (e: Exception) {
                view?.logError(e)
                view?.showErrorMessage(R.string.msg_error)
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


    interface View : BasePresenter.View {
        fun showShoppingProduct(product: ProductView, quantity: Int)
        fun updateShoppingItems()
        fun showShoppingItems()
        fun hideShoppingItems()
        fun showEmptyView()
        fun hideEmptyView()
    }

}