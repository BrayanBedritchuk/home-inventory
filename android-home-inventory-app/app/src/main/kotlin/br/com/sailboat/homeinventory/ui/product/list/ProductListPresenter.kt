package br.com.sailboat.homeinventory.ui.product.list

import android.content.Intent
import br.com.sailboat.homeinventory.R
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
) : BasePresenter<ProductListPresenter.View>() {

    override fun create() {
        loadProducts()
    }

    override fun restart() {
        updateProducts()
    }

    override fun postResult(requestCode: Int, data: Intent?) {
        loadProducts()
    }

    fun onClickProduct(position: Int) {
        val product = viewModel.products[position]
        view?.showProductDetails(product.id)
    }

    fun onClickNewProduct() {
        view?.showInsertProduct()
    }

    fun getProducts() = viewModel.products

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
                view?.showErrorMessage(R.string.msg_error_list)
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


    interface View : BasePresenter.View {
        fun updateProducts()
        fun showProductDetails(productId: Long)
        fun showInsertProduct()
        fun showProducts()
        fun hideProducts()
        fun showEmptyView()
        fun hideEmptyView()
    }

}