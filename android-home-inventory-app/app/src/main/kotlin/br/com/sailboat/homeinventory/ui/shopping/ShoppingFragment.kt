package br.com.sailboat.homeinventory.ui.shopping

import android.app.Activity
import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.helper.EventObserver
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.model.viewholder.ShoppingItemViewHolder

class ShoppingFragment : BaseFragment<ShoppingPresenter>(), ShoppingItemViewHolder.Callback {

    override val layoutId = R.layout.frg_shopping

    override lateinit var presenter: ShoppingPresenter

    private val viewModel = ShoppingViewModel()

    private val recycler by bind<RecyclerView>(R.id.recycler)
    private val toolbar by bind<Toolbar>(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        presenter.viewModel = viewModel
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.menu_save -> {
                onClickCheckout()
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
    }

    override fun observeLiveData() {
        super.observeLiveData()

        viewModel.products.observe(this, Observer {
            (recycler.adapter as ShoppingAdapter).submitList(it)
        })

        viewModel.errorMessage.observe(this, EventObserver {
            Toast.makeText(activity, "Error", Toast.LENGTH_LONG).show()
        })

    }

    override fun onClickShoppingProduct(position: Int) {
        val product: ProductData? = viewModel.products.value?.get(position)
        val quantity = viewModel.shoppingCart[product?.id] ?: 0

        product?.let { showShoppingProduct(it, quantity) }
    }

    override fun wasPurchased(productId: Long) = viewModel.shoppingCart.containsKey(productId)

    override fun getShoppingQuantity(productId: Long) = viewModel.shoppingCart[productId].toString()

    fun showShoppingProduct(product: ProductData, quantity: Int) {
        ShoppingProductDialog.show(
            fragmentManager!!,
            product,
            quantity
        ) { productId: Long, quantity: Int? ->
            onAddProduct(productId, quantity)
        }
    }

    private fun onAddProduct(productId: Long, quantity: Int?) {
        if (viewModel.shoppingCart.containsKey(productId)) {
            viewModel.shoppingCart.remove(productId)
        }

        quantity?.let {
            if (it > 0) {
                viewModel.shoppingCart[productId] = it
            }
        }

        (recycler.adapter as ShoppingAdapter).notifyDataSetChanged()
    }

    fun finishWithSuccess() {
        activity?.setResult(Activity.RESULT_OK)
        activity?.finish()
    }

    private fun onClickCheckout() {
        finishWithSuccess()
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