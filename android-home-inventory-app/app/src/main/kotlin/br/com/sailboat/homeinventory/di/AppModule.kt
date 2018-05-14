package br.com.sailboat.homeinventory.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

//    @Provides
//    @Singleton
//    fun providesRepositoryFactory(repositoryFactory: SQLiteRepositoryFactory): RepositoryFactory =
//        repositoryFactory

}