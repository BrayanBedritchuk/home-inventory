package br.com.sailboat.data

import android.database.sqlite.SQLiteDatabase
import br.com.sailboat.homeinventory.data.table.*

class SQLiteTableCreator {

    private val tables = listOf(
        ProductSQLiteTable(),
        ProductQuantitySQLiteTable(),
        CategorySQLiteTable(),
        ProductCategorySQLiteTable(),
        EstablishmentSQLiteTable(),
        ShoppingSQLiteTable(),
        ShoppingProductSQLiteTable()
    )

    fun createTables(db: SQLiteDatabase) {
        tables.forEach {
            db.execSQL(it.createTableStatement)
        }
    }

}