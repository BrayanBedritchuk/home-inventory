package br.com.sailboat.homeinventory.shopping

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.helper.base.BaseFragment
import br.com.sailboat.homeinventory.presentation.shopping.ShoppingPresenter
import br.com.sailboat.homeinventory.model.ProductModel
import br.com.sailboat.homeinventory.view.shopping.ShoppingAdapter
import br.com.sailboat.homeinventory.view.shopping.ShoppingProductDialog
import br.com.sailboat.homeinventory.view.shopping.ShoppingViewModel
import kotlinx.android.synthetic.main.frg_shopping.*
import javax.inject.Inject

class ShoppingFragment : BaseFragment<ShoppingPresenter>(), ShoppingPresenter.View,
    ShoppingAdapter.Callback {

    override val layoutId = R.layout.frg_shopping

    @Inject
    override lateinit var presenter: ShoppingPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        presenter.view = this
        presenter.viewModel = ViewModelProviders.of(this)
            .get(ShoppingViewModel::class.java)
    }

    override fun initViews() {
        initToolbar()
        initRecyclerView()
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
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
        presenter.onClickShoppingProduct(position)
    }

    override fun wasPurchased(productId: Long) = presenter.wasPurchased(productId)

    override fun getShoppingQuantity(productId: Long) = presenter.getShoppingQuantity(productId)

    override fun showShoppingProduct(product: ProductModel, quantity: Int) {
        ShoppingProductDialog.show(fragmentManager!!, product, quantity,
            object : ShoppingProductDialog.Callback {
                override fun onClickOk(productId: Long, quantity: Int?) {
                    presenter.onAddProduct(productId, quantity)
                }
            })
    }

    override fun getProducts() = presenter.getProducts()

    override fun finishWithSuccess() {
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()
    }

    override fun updateShoppingList() {
        recycler.adapter.notifyDataSetChanged()
    }

    private fun initToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        toolbar.setTitle(R.string.title_shopping)
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
    }

    private fun initRecyclerView() {
        recycler.layoutManager = LinearLayoutManager(activity)
        recycler.adapter = ShoppingAdapter(this)
    }

}