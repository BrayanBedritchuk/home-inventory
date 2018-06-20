package br.com.sailboat.homeinventory.di

import br.com.sailboat.homeinventory.ui.product.details.ProductDetailsFragment
import br.com.sailboat.homeinventory.ui.product.insert.ProductInsertFragment
import br.com.sailboat.homeinventory.ui.product.list.ProductListFragment
import br.com.sailboat.homeinventory.ui.shopping.ShoppingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, UIModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(fragment: ShoppingFragment)
    fun inject(fragment: ProductListFragment)
    fun inject(fragment: ProductDetailsFragment)
    fun inject(fragment: ProductInsertFragment)

}