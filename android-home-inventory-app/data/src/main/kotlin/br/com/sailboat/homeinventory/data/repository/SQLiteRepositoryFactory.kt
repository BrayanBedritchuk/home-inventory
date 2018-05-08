package br.com.sailboat.homeinventory.data.repository

import android.content.Context
import br.com.sailboat.homeinventory.core.repository.ProductRepository
import br.com.sailboat.homeinventory.core.repository.RepositoryFactory
import br.com.sailboat.homeinventory.data.AppDatabase
import javax.inject.Inject

class SQLiteRepositoryFactory @Inject constructor(val context: Context) : RepositoryFactory {

    override fun getProductRepository(): ProductRepository {
        val productSqlite = ProductSQLite(AppDatabase.getInstance(context))
        return ProductSQLiteRepository(productSqlite)
    }

}