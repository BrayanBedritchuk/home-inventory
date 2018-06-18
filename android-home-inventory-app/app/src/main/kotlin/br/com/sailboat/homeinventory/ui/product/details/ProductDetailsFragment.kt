package br.com.sailboat.homeinventory.ui.product.details

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertActivity
import kotlinx.android.synthetic.main.fab.*
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsFragment : BaseFragment<ProductDetailsPresenter>(), ProductDetailsPresenter.View {

    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_details

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.menu_save -> {
                presenter.onClickDelete()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun initViews() {
        initToolbar()
        initRecyclerView()
        initFab()
    }

    override fun showEditProduct(productId: Long) {
        ProductInsertActivity.startEdit(this, productId)
    }

    override fun updateDetails() {
        recycler.adapter.notifyDataSetChanged()
    }

    private fun initToolbar() {
        toolbar.run {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setTitle(R.string.title_product_details)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initRecyclerView() {
        recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductDetailsAdapter(object : ProductDetailsAdapter.Callback {
                override fun getProductDetails() = presenter.getProductDetails()
            })
        }
    }

    private fun initFab() {
        fab.setImageResource(R.drawable.ic_edit_white_24dp)
        fab.setOnClickListener { presenter.onClickEdit() }
    }

}