package br.com.sailboat.homeinventory.view.product.list

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import br.com.sailboat.canoe.base.BaseFragment
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.data.repository.SQLiteRepositoryFactory
import br.com.sailboat.homeinventory.presentation.helper.RequestCode
import br.com.sailboat.homeinventory.view.product.details.ProductDetailsActivity
import br.com.sailboat.homeinventory.view.product.insert.ProductInsertActivity

class ProductListFragment : BaseFragment<ProductListPresenter>(), ProductListPresenter.View,
    ProductListAdapter.Callback {

    override fun getLayoutId() = R.layout.frg_list

    override fun newPresenterInstance() = ProductListPresenter(this, SQLiteRepositoryFactory(activity.applicationContext))

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater!!.inflate(R.menu.menu_search, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun initEmptyViewMessages() {
        emptyViewMessage1 = getString(R.string.no_products_found)
        emptyViewMessage2 = getString(R.string.ept_click_to_add)
    }

    override fun onInitToolbar() {
        toolbar.setTitle(R.string.title_products)
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
        toolbar.setNavigationOnClickListener {
            activity.onBackPressed()
        }
    }

    override fun onInitRecycler() {
        recycler.adapter = ProductListAdapter(this)
    }

    override fun onClickProduct(position: Int) {
        presenter.onClickProduct(position)
    }

    override fun getProducts() = presenter.viewModel.products

    override fun showProductDetails(productId: Long) {
        ProductDetailsActivity.start(this, productId)
    }

    override fun showProductInsert() {
        ProductInsertActivity.start(this)
    }

    override fun onActivityResultOk(requestCode: Int, data: Intent?) {
        when (requestCode) {
            RequestCode.PRODUCT_INSERT.ordinal -> presenter.onResultOkFromProductInsert(data)
        }
    }

    override fun postActivityResult(requestCode: Int, data: Intent?) {
        presenter.postResult()
    }


}