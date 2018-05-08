package br.com.sailboat.homeinventory.helper.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import br.com.sailboat.homeinventory.R

abstract class BaseActivity : AppCompatActivity() {

    private val TAG = "TAG_FRAGMENT"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_base)

        val fragment: Fragment? = supportFragmentManager.findFragmentByTag(TAG)

        if (fragment == null) {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.frame_layout, newFragmentInstance(), TAG)
                .commit()
        }
    }

    abstract fun newFragmentInstance(): Fragment

}