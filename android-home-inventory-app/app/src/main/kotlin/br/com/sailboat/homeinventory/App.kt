package br.com.sailboat.homeinventory

import android.app.Application
import br.com.sailboat.homeinventory.di.AppComponent
import br.com.sailboat.homeinventory.di.AppModule
import br.com.sailboat.homeinventory.di.DaggerAppComponent

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()


        // add LeakCanary
    }
}