package br.com.sailboat.homeinventory.ui.product.list

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.product.details.ProductDetailsActivity
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertActivity
import kotlinx.android.synthetic.main.ept_view.*
import kotlinx.android.synthetic.main.fab.*
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductListFragment : BaseFragment<ProductListPresenter>(), ProductListPresenter.View, ProductListAdapter.Callback {


    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_list

    override fun initViews() {
        initToolbar()
        initRecyclerView()
        initFab()
        initEmptyView()
    }

    override fun updateProducts() {
        (recycler.adapter as ProductListAdapter).notifyDataSetChanged()
    }

    override fun showProductDetails(productId: Long) {
        ProductDetailsActivity.startFrom(this, productId)
    }

    override fun showInsertProduct() {
        ProductInsertActivity.startFrom(this)
    }

    override fun getProducts() = presenter.getProducts()

    override fun onClickProduct(position: Int) {
        presenter.onClickProduct(position)
    }

    override fun showProducts() {
        recycler.visibility = View.VISIBLE
    }

    override fun hideProducts() {
        recycler.visibility = View.GONE
    }

    override fun showEmptyView() {
        llEptView.visibility = View.VISIBLE
    }

    override fun hideEmptyView() {
        llEptView.visibility = View.GONE
    }

    private fun initToolbar() {
        toolbar.run {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setTitle(R.string.title_products)
        }
    }

    private fun initRecyclerView() {
        recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductListAdapter(this@ProductListFragment)
        }
    }

    private fun initFab() {
        fab.setOnClickListener { presenter.onClickNewProduct() }
    }

    private fun initEmptyView() {

    }

}