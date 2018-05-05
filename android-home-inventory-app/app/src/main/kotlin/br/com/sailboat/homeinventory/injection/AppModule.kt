package br.com.sailboat.homeinventory.injection

import android.app.Application
import android.content.Context
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.data.repository.SQLiteRepositoryFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun providesRepositoryFactory(): RepositoryFactory = SQLiteRepositoryFactory(application)

}