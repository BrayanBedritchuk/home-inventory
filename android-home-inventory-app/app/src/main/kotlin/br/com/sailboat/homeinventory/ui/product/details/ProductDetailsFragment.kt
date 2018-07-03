package br.com.sailboat.homeinventory.ui.product.details

import android.content.DialogInterface
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import br.com.sailboat.homeinventory.App
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.ui.base.BaseFragment
import br.com.sailboat.homeinventory.ui.dialog.OptionDialog
import br.com.sailboat.homeinventory.ui.helper.Extras
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertActivity
import kotlinx.android.synthetic.main.fab.*
import kotlinx.android.synthetic.main.recycler.*
import kotlinx.android.synthetic.main.toolbar.*

class ProductDetailsFragment : BaseFragment<ProductDetailsContract.Presenter>(), ProductDetailsContract.View {

    override fun inject() {
        (activity?.application as App).appComponent.inject(this)
    }

    override fun getLayoutId() = R.layout.frg_details

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.menu_delete, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?) = when (item?.itemId) {
        R.id.menu_delete -> {
            presenter.onClickDelete()
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    override fun initViews() {
        initToolbar()
        initRecyclerView()
        initFab()
    }

    override fun extractProductId() = Extras.getProductId(arguments)

    override fun navigateToEditProduct(productId: Long) {
        ProductInsertActivity.startEdit(this, productId)
    }

    override fun updateDetails() {
        recycler.adapter.notifyDataSetChanged()
    }

    override fun closeWithFailureOnLoadDetails() {
        closeWithFailure(R.string.error_msg_details)
    }

    override fun showDeleteMessage() {
        val dialog = OptionDialog()
        dialog.message = R.string.msg_delete_product
        dialog.yesOption = R.string.delete
        dialog.onClickPositive = DialogInterface.OnClickListener { _, _ ->
            presenter.onClickYesOnDeleteProduct()
        }
        dialog.show(fragmentManager, "DELETE_DIALOG")
    }

    override fun closeWithSuccessOnDeleteProduct() {
        closeWithSuccess(R.string.feedback_msg_product_deleted_successfully)
    }

    override fun showErrorMessageOnDeleteProduct() {
        showErrorMessage(R.string.error_msg_delete_product)
    }

    private fun initToolbar() {
        toolbar.run {
            (activity as AppCompatActivity).setSupportActionBar(this)
            setTitle(R.string.title_product_details)
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
        }
    }

    private fun initRecyclerView() {
        recycler.run {
            layoutManager = LinearLayoutManager(activity)
            adapter = ProductDetailsAdapter(object : ProductDetailsAdapter.Callback {
                override fun getProductDetails() = presenter.getProductDetails()
            })
        }
    }

    private fun initFab() {
        fab.setImageResource(R.drawable.ic_edit_white_24dp)
        fab.setOnClickListener { presenter.onClickEdit() }
    }

}