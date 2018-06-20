package br.com.sailboat.homeinventory.ui.product.list

import br.com.sailboat.homeinventory.ui.base.BaseMvpContract
import br.com.sailboat.homeinventory.ui.model.ProductView

interface ProductListContract {

    interface View : BaseMvpContract.View {
        fun updateProducts()
        fun showProducts()
        fun showEmptyView()
        fun showErrorLoadingProducts()
        fun hideProducts()
        fun hideEmptyView()
        fun navigateToProductDetails(productId: Long)
        fun navigateToInsertProduct()
        fun navigateToShopping()
    }

    interface Presenter : BaseMvpContract.Presenter {
        fun onClickNewProduct()
        fun onClickProduct(position: Int)
        fun getProducts(): List<ProductView>
        fun onClickShopping()
    }

}