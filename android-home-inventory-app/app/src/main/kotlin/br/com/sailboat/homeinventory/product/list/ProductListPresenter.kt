package br.com.sailboat.homeinventory.view.product.list

import android.content.Intent
import android.support.design.widget.Snackbar
import br.com.sailboat.canoe.base.BasePresenter
import br.com.sailboat.canoe.helper.AsyncHelper
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.core.entity.Product
import br.com.sailboat.homeinventory.core.interactor.GetProducts
import br.com.sailboat.homeinventory.core.interactor.UseCaseWithResponse
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory


class ProductListPresenter(view: View, val repositoryFactory: RepositoryFactory) :
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

            lateinit var products: List<Product>

            @Throws(Exception::class)
            override fun doInBackground() {

                GetProducts(repositoryFactory.productRepository, viewModel.filter).execute(
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