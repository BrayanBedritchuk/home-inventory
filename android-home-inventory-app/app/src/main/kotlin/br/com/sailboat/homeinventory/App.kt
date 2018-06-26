package br.com.sailboat.homeinventory

import android.app.Application
import br.com.sailboat.homeinventory.di.*

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .uIModule(UIModule())
                .repositoryModule(RepositoryModule())
                .build()


        // add LeakCanary
    }
}