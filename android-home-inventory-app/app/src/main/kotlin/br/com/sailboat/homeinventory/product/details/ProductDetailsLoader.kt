package br.com.sailboat.homeinventory.view.product.details

import android.content.Context
import br.com.sailboat.homeinventory.core.repository.ProductRepository
import br.com.sailboat.homeinventory.R
import br.com.sailboat.domain.ProductLoader
import br.com.sailboat.homeinventory.presentation.helper.ViewType
import br.com.sailboat.homeinventory.presentation.model.LabelAndValueModel
import br.com.sailboat.homeinventory.presentation.model.RecyclerViewItem
import br.com.sailboat.homeinventory.presentation.model.TitleModel

class ProductDetailsLoader(
    private val context: Context,
    private val productRepository: ProductRepository
) {

    fun loadProductDetailsViews(productId: Long): List<RecyclerViewItem> {
        val product = ProductLoader(productRepository).loadProduct(productId)

        val productDetails = ArrayList<RecyclerViewItem>()

        var title = TitleModel(ViewType.TITLE.ordinal, product.name)

        var label = LabelAndValueModel(
            ViewType.LABEL_VALUE.ordinal,
            context.getString(R.string.label_quantity),
            product.quantity.toString()
        )

        productDetails.add(title)
        productDetails.add(label)

        return productDetails
    }

}