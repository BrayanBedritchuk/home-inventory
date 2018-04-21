package br.com.sailboat.homeinventory.view.product.insert

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.sailboat.canoe.base.BaseFragment
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.presentation.helper.Extras
import kotlinx.android.synthetic.main.frg_product_insert.*

class ProductInsertFragment : BaseFragment<ProductInsertPresenter>(), ProductInsertPresenter.View {


    companion object {

        fun newInstance() = ProductInsertFragment()

        fun newInstanceWithProductToEdit(productId: Long): ProductInsertFragment {
            val args = Bundle()
            Extras.putProductId(args, productId)
            val fragment = ProductInsertFragment()
            fragment.arguments = args

            return fragment
        }

    }

    override fun getLayoutId() = R.layout.frg_product_insert

    override fun newPresenterInstance() = ProductInsertPresenter(this)

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.getItemId()) {
            R.id.menu_save -> {
                presenter.onClickMenuSave()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onInitToolbar() {
        toolbar.setNavigationIcon(R.drawable.ic_close_white_24dp)
        toolbar.setNavigationOnClickListener { activity.onBackPressed() }
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

}