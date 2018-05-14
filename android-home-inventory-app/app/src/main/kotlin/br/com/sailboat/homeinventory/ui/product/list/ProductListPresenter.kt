package br.com.sailboat.homeinventory.ui.product.list

import android.content.Intent
import android.support.design.widget.Snackbar
import br.com.sailboat.canoe.base.BasePresenter
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.helper.AsyncHelper


class ProductListPresenter(view: View, val repositoryFactory: RepositoryFactory?) :
    BasePresenter<ProductListPresenter.View>(view) {

    val viewModel = ProductListViewModel()

    override fun postResume() {
        loadProducts()
    }

    override fun onQueryTextChange() {
        viewModel.filter.searchQuery = searchText
        loadProducts()
    }

    override fun onClickFab() {
        view.showProductInsert()
    }

    fun onClickProduct(position: Int) {
        val product = viewModel.products[position]
        view.showProductDetails(product.id)
    }

    fun onResultOkFromProductInsert(data: Intent?) {
        view.showSnackbar(getString(R.string.product_added), Snackbar.LENGTH_LONG)
    }

    fun postResult() {
        loadProducts()
    }

    private fun loadProducts() {
        AsyncHelper.execute(object : AsyncHelper.Callback {

            var products = mutableListOf<Product>()

            override fun doInBackground() {
//                products = GetProducts(repositoryFactory.productRepository, viewModel.filter).execute()
            }

            override fun onSuccess() {
                viewModel.products.clear()
                viewModel.products.addAll(products)
                updateContentViews()
            }

            override fun onFail(throwable: Throwable) {
//                printLogAndShowDialog(throwable)
                updateContentViews()
            }
        })
    }

    private fun updateContentViews() {
        view.updateRecycler()
        updateRecyclerVisibility()
    }

    private fun updateRecyclerVisibility() {
        if (viewModel.products.isEmpty()) {
            view.hideRecycler()
            view.showEmptyView()
        } else {
            view.hideEmptyView()
            view.showRecycler()
        }
    }


    interface View : BasePresenter.View {
        fun showProductDetails(productId: Long)
        fun showProductInsert()
    }


}