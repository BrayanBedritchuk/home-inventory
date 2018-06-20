package br.com.sailboat.homeinventory.ui.product.insert

import br.com.sailboat.homeinventory.ui.base.BaseMvpContract

interface ProductInsertContract {

    interface View : BaseMvpContract.View {
        fun extractProductId(): Long
        fun getName(): String
        fun getQuantity(): String
        fun setName(name: String)
        fun setQuantity(quantity: String)
        fun showErrorOnSaveProduct()
        fun closeWithSuccessOnEditProduct()
        fun closeWithSuccessOnInsertProduct()
        fun setTitleNewProduct()
        fun setTitleEditProduct()
        fun showErrorNameNotFilled()
        fun showErrorQuantityNegative()
    }

    interface Presenter : BaseMvpContract.Presenter {
        fun onClickSave()
    }

}