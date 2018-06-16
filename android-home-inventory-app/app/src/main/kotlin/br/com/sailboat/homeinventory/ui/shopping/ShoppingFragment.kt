package br.com.sailboat.homeinventory.ui.shopping

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.model.ProductView
import kotlinx.android.synthetic.main.ept_view.*
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.toolbar.*

class ShoppingFragment : BaseFragment<ShoppingPresenter>(), ShoppingPresenter.View, ShoppingAdapter.Callback {


    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_shopping

    override fun initViews() {
        initToolbar()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_save -> {
                presenter.onClickCheckout()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onClickShoppingProduct(position: Int) {
        presenter.onClickProduct(position)
    }

    override fun showShoppingProduct(product: ProductView, quantity: Int) {
        ShoppingProductDialog.show(
            fragmentManager!!,
            product,
            quantity
        ) { productId: Long, quantity: Int ->
            presenter.onAddProduct(productId, quantity)
        }
    }

    override fun wasPurchased(productId: Long) = presenter.wasPurchased(productId)

    override fun getShoppingQuantity(productId: Long) = presenter.getShoppingQuantity(productId)

    override fun getShoppingItems() = presenter.getShoppingItems()

    override fun updateShoppingItems() {
        (recycler.adapter as ShoppingAdapter).notifyDataSetChanged()
    }

    override fun showShoppingItems() {
        recycler.visibility = View.VISIBLE
    }

    override fun hideShoppingItems() {
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
            setTitle(R.string.title_shopping)
            setNavigationIcon(R.drawable.ic_close_white_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

    private fun initRecyclerView() {
        recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ShoppingAdapter(this@ShoppingFragment)
        }
    }

}