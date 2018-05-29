package br.com.sailboat.homeinventory.di

import android.content.Context
import br.com.sailboat.homeinventory.data.AppDatabase
import br.com.sailboat.homeinventory.data.repository.ProductRoomRepository
import br.com.sailboat.homeinventory.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesAppDatabase(context: Context): AppDatabase = AppDatabase.getInstance(context)

    @Provides
    @Singleton
    fun providesProductRepository(appDatabase: AppDatabase): ProductRepository {
        return ProductRoomRepository(appDatabase.productDao())
    }

}