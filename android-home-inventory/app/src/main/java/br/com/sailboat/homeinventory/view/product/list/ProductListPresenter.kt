package br.com.sailboat.homeinventory.view.product.list

import android.content.Intent
import android.support.design.widget.Snackbar
import br.com.sailboat.canoe.base.BasePresenter
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.interactor.loader.ProductLoader
import br.com.sailboat.homeinventory.model.Product
import java.util.*


class ProductListPresenter(view: View) : BasePresenter<ProductListPresenter.View>(view) {

    val viewModel = ProductListViewModel()

    override fun postResume() {
        loadProducts()
    }

    override fun onQueryTextChange() {
        viewModel.filter.searchText = searchText
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

            internal var products: List<Product> = ArrayList()

            @Throws(Exception::class)
            override fun doInBackground() {
                products = ProductLoader(context).loadProducts(viewModel.filter)
            }

            override fun onSuccess() {
                viewModel.products.clear()
                viewModel.products.addAll(products)
                updateContentViews()
            }

            override fun onFail(e: Exception) {
                printLogAndShowDialog(e)
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