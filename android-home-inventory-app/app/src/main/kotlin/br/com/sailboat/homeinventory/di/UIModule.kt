package br.com.sailboat.homeinventory.di

import br.com.sailboat.homeinventory.ui.product.details.ProductDetailsContract
import br.com.sailboat.homeinventory.ui.product.details.ProductDetailsPresenter
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertContract
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertPresenter
import br.com.sailboat.homeinventory.ui.product.list.ProductListContract
import br.com.sailboat.homeinventory.ui.product.list.ProductListPresenter
import br.com.sailboat.homeinventory.ui.shopping.ShoppingContract
import br.com.sailboat.homeinventory.ui.shopping.ShoppingPresenter
import dagger.Module
import dagger.Provides

@Module
class UIModule {

    @Provides
    fun providesProductListPresenter(presenter: ProductListPresenter): ProductListContract.Presenter {
        return presenter
    }

    @Provides
    fun providesProductDetailsPresenter(presenter: ProductDetailsPresenter): ProductDetailsContract.Presenter {
        return presenter
    }

    @Provides
    fun providesProductInsertPresenter(presenter: ProductInsertPresenter): ProductInsertContract.Presenter {
        return presenter
    }

    @Provides
    fun providesShoppingPresenter(presenter: ShoppingPresenter): ShoppingContract.Presenter {
        return presenter
    }

}