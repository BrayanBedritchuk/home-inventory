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
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.domain.LogcatLogger
import br.com.sailboat.homeinventory.presentation.model.ProductModel
import br.com.sailboat.homeinventory.presentation.shopping.ShoppingPresenter
import javax.inject.Inject

class ShoppingActivity : AppCompatActivity(), ShoppingPresenter.View, ShoppingAdapter.Callback {

    @Inject
    lateinit var repositoryFactory: RepositoryFactory
    lateinit var presenter: ShoppingPresenter
    private lateinit var viewModel: ShoppingViewModel
    private lateinit var recycler: RecyclerView

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ShoppingActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_shopping)
        (application as App).appComponent.inject(this)

        initViewModel()
        initPresenter()
        initViews()
        initObservers()

        presenter.onCreate()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_save, menu)
        return true
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
        ShoppingProductDialog.show(supportFragmentManager, product, quantity,
            object : ShoppingProductDialog.Callback {
                override fun onClickOk(productId: Long, quantity: Int?) {
                    presenter.onAddProduct(productId, quantity)
                }
            })
    }

    override fun getProducts() = viewModel.getProducts()

    override fun finishWithSuccess() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    override fun updateShoppingList() {
        recycler.adapter.notifyDataSetChanged()
    }

    private fun initViews() {
        initToolbar()
        initRecycler()
    }

    private fun initToolbar() {
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        toolbar.setTitle(R.string.title_shopping)
        setSupportActionBar(toolbar)
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }

    private fun initRecycler() {
        recycler = findViewById<RecyclerView>(R.id.recycler)
        recycler.layoutManager = LinearLayoutManager(this)
        recycler.adapter = ShoppingAdapter(this)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ShoppingViewModel::class.java)
    }

    private fun initPresenter() {
        presenter = ShoppingPresenter(
            this,
            viewModel,
            repositoryFactory,
            LogcatLogger()
        )
    }

    private fun initObservers() {
        viewModel.products.observe(this, Observer { recycler.adapter.notifyDataSetChanged() })
    }

}