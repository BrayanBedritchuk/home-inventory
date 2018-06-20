package br.com.sailboat.homeinventory.ui.product.details

import br.com.sailboat.homeinventory.ui.base.BaseMvpContract
import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem

interface ProductDetailsContract {

    interface View : BaseMvpContract.View {
        fun extractProductId(): Long
        fun navigateToEditProduct(productId: Long)
        fun updateDetails()
        fun closeWithFailureOnLoadDetails()
    }

    interface Presenter : BaseMvpContract.Presenter {
        fun onClickDelete()
        fun getProductDetails(): List<RecyclerViewItem>
        fun onClickEdit()
    }

}