package br.com.sailboat.homeinventory.injection

import br.com.sailboat.homeinventory.view.shopping.ShoppingActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(activity: ShoppingActivity)
}