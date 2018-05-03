package br.com.sailboat.homeinventory.shopping

import android.content.Context
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.data.repository.SQLiteRepositoryFactory
import dagger.Module
import dagger.Provides

@Module
class ShoppingModule {

    @Provides
    fun provideRepositoryFactory(context: Context) : RepositoryFactory {
        return SQLiteRepositoryFactory(context)
    }

}