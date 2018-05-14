package br.com.sailboat.homeinventory.di

import br.com.sailboat.homeinventory.ui.shopping.ShoppingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: ShoppingFragment)
}