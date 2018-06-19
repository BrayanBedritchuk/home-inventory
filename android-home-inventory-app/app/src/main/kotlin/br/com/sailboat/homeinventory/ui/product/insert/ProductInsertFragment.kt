package br.com.sailboat.homeinventory.ui.product.insert

import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.helper.Extras
import kotlinx.android.synthetic.main.frg_product_insert.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductInsertFragment : BaseFragment<ProductInsertPresenter>(), ProductInsertPresenter.View {

    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_product_insert

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.menu_save -> {
                presenter.onClickSave()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun extractProductId() = Extras.getProductId(arguments)

    override fun initViews() {
        initToolbar()
    }

    override fun getName() = etName.text.toString()

    override fun getQuantity() = etQuantity.text.toString()

    override fun setName(name: String) {
        etName.setText(name)
        etName.setSelection(etName.length())
    }

    override fun setQuantity(quantity: String) {
        etQuantity.setText(quantity)
    }

    override fun setTitle(title: Int) {
        toolbar.setTitle(title)
    }

    private fun initToolbar() {
        toolbar.run {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setNavigationIcon(R.drawable.ic_close_white_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

}