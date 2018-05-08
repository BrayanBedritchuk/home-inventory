package br.com.sailboat.homeinventory.helper.injection

import br.com.sailboat.homeinventory.shopping.ShoppingFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: ShoppingFragment)
}