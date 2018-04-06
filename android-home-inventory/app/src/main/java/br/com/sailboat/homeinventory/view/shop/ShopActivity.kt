package br.com.sailboat.homeinventory.view.shop

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.view.product.list.ProductListAdapter

class ShopActivity : AppCompatActivity(), ShopPresenter.View, ProductListAdapter.Callback {

    private lateinit var viewModel: ShopViewModel
    private lateinit var presenter: ShopPresenter
    private lateinit var recycler: RecyclerView

    private val layoutId = R.layout.act_shop

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ShopActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        initViewModel()
        initPresenter()
        initViews()
        initObservers()
        presenter.onCreate(savedInstanceState)
    }

    override fun showProductDetails(productId: Long) {

    }

    override fun getProducts() = viewModel.getProducts()

    override fun onClickProduct(position: Int) {
        presenter.onClickProduct(position)
    }

    private fun initViews() {
        findViewById<Toolbar>(R.id.toolbar).setTitle(R.string.title_shop)
        recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ProductListAdapter(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ShopViewModel::class.java)
    }

    private fun initPresenter() {
        presenter = ShopPresenter(this, viewModel)
    }

    private fun initObservers() {
        viewModel.products.observe(this, Observer { recycler.adapter.notifyDataSetChanged() })
    }

}