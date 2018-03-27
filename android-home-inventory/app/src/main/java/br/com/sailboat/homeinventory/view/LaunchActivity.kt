package br.com.sailboat.homeinventory.view

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.view.product.list.ProductListActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ProductListActivity.start(this)
        finish()
    }

}