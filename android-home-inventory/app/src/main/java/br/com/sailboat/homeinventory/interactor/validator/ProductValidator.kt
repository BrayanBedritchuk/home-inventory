package br.com.sailboat.homeinventory.interactor.validator

import android.content.Context
import br.com.sailboat.canoe.exception.RequiredFieldNotFilledException
import br.com.sailboat.canoe.helper.StringHelper
import br.com.sailboat.homeinventory.R
import br.com.sailboat.homeinventory.model.Product

class ProductValidator {

    companion object {

        fun validate(context: Context, product: Product) {
            if (StringHelper.isNullOrEmpty(product.name)) {
                throw RequiredFieldNotFilledException(context.getString(R.string.exception_product_name))
            }

        }

    }
}