package br.com.sailboat.homeinventory.ui.product.details

import android.content.Context
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.domain.usecase.GetProduct
import br.com.sailboat.homeinventory.domain.usecase.UseCase2
import br.com.sailboat.homeinventory.ui.model.LabelAndValueModel
import br.com.sailboat.homeinventory.ui.model.RecyclerViewItem
import br.com.sailboat.homeinventory.ui.model.TitleModel
import br.com.sailboat.homeinventory.ui.model.ViewType
import javax.inject.Inject

class GetProductDetails @Inject constructor(
    private val getProduct: GetProduct,
    private val context: Context
) : UseCase2<Long, List<RecyclerViewItem>>() {

    override fun execute(productId: Long): List<RecyclerViewItem> {
        val product = getProduct.execute(productId)

        val productDetails = mutableListOf<RecyclerViewItem>()

        var title = TitleModel(
            ViewType.TITLE.ordinal,
            product.name
        )

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