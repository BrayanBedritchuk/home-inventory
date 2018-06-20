package br.com.sailboat.homeinventory.ui.product.list

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.product.details.ProductDetailsActivity
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertActivity
import br.com.sailboat.homeinventory.ui.shopping.ShoppingActivity
import kotlinx.android.synthetic.main.ept_view.*
import kotlinx.android.synthetic.main.fab.*
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductListFragment : BaseFragment<ProductListContract.Presenter>(), ProductListContract.View {

    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_list

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_product_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_new_product -> {
                presenter.onClickNewProduct()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun initViews() {
        initToolbar()
        initRecyclerView()
        initFab()
        initEmptyView()
    }

    override fun updateProducts() {
        (recycler.adapter as ProductListAdapter).notifyDataSetChanged()
    }

    override fun navigateToProductDetails(productId: Long) {
        ProductDetailsActivity.startFrom(this, productId)
    }

    override fun navigateToInsertProduct() {
        ProductInsertActivity.startFrom(this)
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

    override fun showErrorLoadingProducts() {
        showErrorMessage(R.string.error_msg_loading_products)
    }

    override fun navigateToShopping() {
        ShoppingActivity.startFrom(this)
    }

    private fun initToolbar() {
        toolbar.run {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setTitle(R.string.app_name)
        }
    }

    private fun initRecyclerView() {
        recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductListAdapter(object : ProductListAdapter.Callback {
                override fun getProducts() = presenter.getProducts()
                override fun onClickProduct(position: Int) = presenter.onClickProduct(position)
            })
        }
    }

    private fun initFab() {
        fab.setImageResource(R.drawable.ic_cart)
        fab.setOnClickListener { presenter.onClickShopping() }
    }

    private fun initEmptyView() {
        // TODO
    }

}