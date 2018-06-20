package br.com.sailboat.homeinventory.ui.product.list

import br.com.sailboat.homeinventory.ui.base.BaseMvpContract
import br.com.sailboat.homeinventory.ui.model.ProductView

interface ProductListContract {

    interface View : BaseMvpContract.View {
        fun updateProducts()
        fun showProductDetails(productId: Long)
        fun showInsertProduct()
        fun showProducts()
        fun hideProducts()
        fun showEmptyView()
        fun hideEmptyView()
        fun showErrorLoadingProducts()
    }

    interface Presenter : BaseMvpContract.Presenter {
        fun onClickNewProduct()
        fun onClickProduct(position: Int)
        fun getProducts(): List<ProductView>
    }

}