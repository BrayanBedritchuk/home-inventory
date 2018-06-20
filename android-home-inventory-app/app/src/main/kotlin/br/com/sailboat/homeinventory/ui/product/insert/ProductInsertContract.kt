package br.com.sailboat.homeinventory.ui.product.insert

import br.com.sailboat.homeinventory.ui.base.BaseMvpContract

interface ProductInsertContract {

    interface View : BaseMvpContract.View {
        fun extractProductId(): Long
        fun getName(): String
        fun getQuantity(): String
        fun setName(name: String)
        fun setQuantity(quantity: String)
        fun setTitleNewProduct()
        fun setTitleEditProduct()
        fun showErrorOnSaveProduct()
        fun showErrorNameNotFilled()
        fun showErrorQuantityNegative()
        fun closeWithSuccessOnEditProduct()
        fun closeWithSuccessOnInsertProduct()
    }

    interface Presenter : BaseMvpContract.Presenter {
        fun onClickSave()
    }

}