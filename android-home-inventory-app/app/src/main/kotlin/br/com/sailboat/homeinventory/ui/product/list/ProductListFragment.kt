package br.com.sailboat.homeinventory.ui.product.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.helper.EventObserver
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

class ProductListFragment : BaseFragment<ProductListPresenter>() {

    override val layoutId = R.layout.frg_list

    @Inject
    override lateinit var presenter: ProductListPresenter
    private val events = ProductListEvents()
    private val viewModel = ProductListViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        presenter.viewModel = viewModel
        presenter.events = events
    }

    override fun initViews() {
        initToolbar()
        initRecyclerView()
    }

    override fun observeLiveData() {
        if (viewModel.products.hasObservers()) {
            Log.d("ProductListFragment", "product hasObservers")
        }

        viewModel.products.observe(this, Observer { product ->
            (recycler.adapter as ProductListAdapter).collection = product.orEmpty()
        })

        viewModel.error.observe(this, EventObserver { msg ->
            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
        })

        viewModel.startAsync.observe(this, EventObserver {
            showProgress()
        })

        viewModel.finishAsync.observe(this, EventObserver {
            hideProgress()
        })

        if (events.productDetails.hasObservers()) {
            Log.d("ProductListFragment", "events.productDetails hasObservers")
        }

        events.productDetails.observe(this, EventObserver {
            Toast.makeText(activity, "Show Product Details $it", Toast.LENGTH_LONG).show()
        })

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
            adapter = ProductListAdapter()
        }

        (recycler.adapter as ProductListAdapter).onClickProduct = { position ->
            presenter.onClickProduct(position)
        }
    }

}