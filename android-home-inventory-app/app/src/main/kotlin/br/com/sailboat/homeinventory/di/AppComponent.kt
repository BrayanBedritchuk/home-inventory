package br.com.sailboat.homeinventory.di

import br.com.sailboat.homeinventory.ui.product.list.ProductListFragment
import br.com.sailboat.homeinventory.ui.shopping.ShoppingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, RepositoryModule::class])
interface AppComponent {

    fun inject(fragment: ShoppingFragment)
    fun inject(fragment: ProductListFragment)
}