package br.com.sailboat.homeinventory.ui.product.list

import br.com.sailboat.homeinventory.domain.None
import br.com.sailboat.homeinventory.domain.usecase.GetProducts
import br.com.sailboat.homeinventory.ui.base.BasePresenter
import br.com.sailboat.homeinventory.ui.model.ProductViewMapper
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import javax.inject.Inject

class ProductListPresenter @Inject constructor(
        private val viewModel: ProductListViewModel,
        private val getProducts: GetProducts
) : BasePresenter<ProductListContract.View>(), ProductListContract.Presenter {

    override fun create() {
        loadProducts()
    }

    override fun restart() {
        updateProducts()
    }

    override fun postResult() {
        super.postResult()
        loadProducts()
    }

    override fun onClickProduct(position: Int) {
        val product = viewModel.products[position]
        view?.navigateToProductDetails(product.id)
    }

    override fun onClickNewProduct() {
        view?.navigateToInsertProduct()
    }

    override fun getProducts() = viewModel.products

    override fun onClickShopping() {
        view?.navigateToShopping()
    }

    private fun loadProducts() {
        launch(UI) {
            try {
                view?.showProgress()
                val products = async(CommonPool) { getProducts.execute(None()) }.await()

                viewModel.products.clear()
                viewModel.products.addAll(ProductViewMapper().transform(products))

                updateProducts()
            } catch (e: Exception) {
                view?.logError(e)
                view?.showErrorLoadingProducts()
            } finally {
                view?.hideProgress()
            }
        }
    }

    private fun updateProducts() {
        view?.updateProducts()

        if (viewModel.products.isEmpty()) {
            view?.hideProducts()
            view?.showEmptyView()
        } else {
            view?.showProducts()
            view?.hideEmptyView()
        }
    }

}