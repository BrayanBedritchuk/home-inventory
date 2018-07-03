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

class ProductInsertFragment : BaseFragment<ProductInsertContract.Presenter>(), ProductInsertContract.View {

    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_product_insert

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.menu_save -> {
            presenter.onClickSave()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
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

    override fun showErrorOnSaveProduct() {
        showErrorMessage(R.string.error_msg)
    }

    override fun closeWithSuccessOnEditProduct() {
        closeWithSuccess(R.string.feedback_msg_product_edited_successfully)
    }

    override fun closeWithSuccessOnInsertProduct() {
        closeWithSuccess(R.string.feedback_msg_product_inserted_successfully)
    }

    override fun setTitleNewProduct() {
        toolbar.setTitle(R.string.title_new_product)
    }

    override fun setTitleEditProduct() {
        toolbar.setTitle(R.string.title_edit_product)
    }

    override fun showErrorNameNotFilled() {
        showErrorMessage(R.string.error_msg_product_name_not_filled)
    }

    override fun showErrorQuantityNegative() {
        showErrorMessage(R.string.product_quantity_cant_be_negative)
    }

    private fun initToolbar() {
        toolbar.run {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setNavigationIcon(R.drawable.ic_close_white_24dp)
            setNavigationOnClickListener { activity?.onBackPressed() }
        }
    }

}