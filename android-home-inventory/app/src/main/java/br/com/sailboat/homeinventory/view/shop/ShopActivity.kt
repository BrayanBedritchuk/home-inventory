package br.com.sailboat.homeinventory.view.shop

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import br.com.sailboat.homeinventory.R

class ShopActivity : AppCompatActivity() {

    private lateinit var viewModel: ShopViewModel

    companion object {
        fun start(activity: AppCompatActivity) {
            val intent = Intent(activity, ShopActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_shop)
        initViews()
        initViewModel()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        viewModel.loadProducts()
    }

    private fun initViews() {
        findViewById<Toolbar>(R.id.toolbar).setTitle(R.string.title_shop)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(ShopViewModel::class.java)
        viewModel.name.observe(this, Observer { /* tvName.text = it*/ })
    }

}