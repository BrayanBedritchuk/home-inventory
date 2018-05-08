package br.com.sailboat.homeinventory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.view.dashboard.DashboardActivity

class LaunchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DashboardActivity.startFrom(this)
        finish()
    }

}