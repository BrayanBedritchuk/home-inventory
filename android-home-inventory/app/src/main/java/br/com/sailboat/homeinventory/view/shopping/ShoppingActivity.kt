package br.com.sailboat.homeinventory.view.shopping

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.model.Product
import br.com.sailboat.homeinventory.view.product.list.ProductListAdapter


class ShoppingActivity : AppCompatActivity(), ShoppingPresenter.View, ProductListAdapter.Callback {

    private lateinit var viewModel: ShoppingViewModel
    private lateinit var presenter: ShoppingPresenter
    private lateinit var recycler: RecyclerView

    private val layoutId = R.layout.act_shop

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ShoppingActivity::class.java)
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.menu_save -> {
                presenter.onClickMenuSave()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }

    }

    override fun showShopProduct(product: Product, quantity: Int) {
        ShoppingProductDialog.show(supportFragmentManager, product, quantity,
            object : ShoppingProductDialog.Callback {
                override fun onClickOk(productId: Long, quantity: Int?) {
                    presenter.onClickOkProductDialog(productId, quantity)
                }
            })
    }

    override fun getProducts() = viewModel.getProducts()

    override fun onClickProduct(position: Int) {
        presenter.onClickProduct(position)
    }

    override fun finishActivity() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    private fun initViews() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.title_shop)
        setSupportActionBar(toolbar)

        recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ProductListAdapter(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ShoppingViewModel::class.java)
    }

    private fun initPresenter() {
        presenter = ShoppingPresenter(this, viewModel)
    }

    private fun initObservers() {
        viewModel.products.observe(this, Observer { recycler.adapter.notifyDataSetChanged() })
    }

}