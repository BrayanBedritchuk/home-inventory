package br.com.sailboat.homeinventory.ui.product.list

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment

class ProductListFragment : BaseFragment<ProductListViewModel>() {

    override val layoutId = R.layout.frg_list

    private val recycler by bind<RecyclerView>(R.id.recycler)
    private val toolbar by bind<Toolbar>(R.id.toolbar)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)

        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(ProductListViewModel::class.java)
    }

    override fun initViews() {
        initToolbar()
        initRecyclerView()
    }

    override fun subscribeToViewModelEvents() {
        viewModel.products.observe(this, Observer { product ->
            (recycler.adapter as ProductListAdapter).collection = product.orEmpty()
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

        (recycler.adapter as ProductListAdapter).onClickProduct = {
            viewModel.products?.value?.get(it)?.id?.let {
                // ProductDetails.start(this, it)
            }
        }
    }

}