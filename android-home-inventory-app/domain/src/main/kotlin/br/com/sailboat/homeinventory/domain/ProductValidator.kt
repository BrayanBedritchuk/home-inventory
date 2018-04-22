package br.com.sailboat.homeinventory.domain

import android.content.Context
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException
import br.com.sailboat.canoe.helper.StringHelper
import br.com.sailboat.homeinventory.core.entity.Product

class ProductValidator {

    companion object {

        fun validate(context: Context, product: Product) {
            if (StringHelper.isNullOrEmpty(product.name)) {
                throw RequiredFieldNotFilledException(context.getString(R.string.exception_product_name))
            }

        }

    }
}