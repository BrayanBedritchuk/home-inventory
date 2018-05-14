package br.com.sailboat.homeinventory.ui.shopping

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.data.ProductData
import br.com.sailboat.homeinventory.helper.EventObserver
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.view.shopping.ShoppingItemViewHolder
import kotlinx.android.synthetic.main.frg_shopping.*

class ShoppingFragment : BaseFragment<ShoppingViewModel>(), ShoppingItemViewHolder.Callback {

    override val layoutId = R.layout.frg_shopping
    override lateinit var viewModel: ShoppingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        // TODO: Inject ViewModel and productRepository with Dagger
        viewModel = ViewModelProviders.of(this).get(ShoppingViewModel::class.java)
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

    override fun subscribeToViewModelEvents() {
        super.subscribeToViewModelEvents()

        viewModel.products.observe(this, Observer {
            (recycler.adapter as ShoppingAdapter).submitList(it)
        })

        viewModel.errorMessage.observe(this, EventObserver {
            Toast.makeText(activity, "Aeee", Toast.LENGTH_LONG).show()
        })

    }

    override fun onClickShoppingProduct(position: Int) {
        var product: ProductData? = viewModel?.products?.value?.get(position)
        val quantity = viewModel?.shoppingCart[product?.id] ?: 0

        product?.let { showShoppingProduct(product, quantity) }
    }

    override fun wasPurchased(productId: Long) = viewModel.shoppingCart.containsKey(productId)

    override fun getShoppingQuantity(productId: Long) = viewModel.shoppingCart[productId].toString()

    fun showShoppingProduct(product: ProductData, quantity: Int) {
        ShoppingProductDialog.show(fragmentManager!!, product, quantity,
            object : ShoppingProductDialog.Callback {
                override fun onClickOk(productId: Long, quantity: Int?) {
                    onAddProduct(productId, quantity)
                }
            })
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