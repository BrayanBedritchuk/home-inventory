package br.com.sailboat.homeinventory

import android.app.Application
import br.com.sailboat.homeinventory.helper.injection.AppComponent
import br.com.sailboat.homeinventory.helper.injection.AppModule
import br.com.sailboat.homeinventory.helper.injection.DaggerAppComponent

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