package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.canoe.helper.CreateTablesHelper
import br.com.sailboat.canoe.helper.LogHelper
import java.util.*

class AppDatabase private constructor(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        try {
            CreateTablesHelper.createTables(db, getSqliteTables())
        } catch (e: Exception) {
            LogHelper.logException(e)
        }

    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // TODO
    }

    fun getSqliteTables(): List<BaseSQLite> {
        val tables = ArrayList<BaseSQLite>()
        tables.add(ProductDao.newInstance(context))
        tables.add(ProductQuantityDao.newInstance(context))

        return tables
    }

    companion object {
        private val DATABASE_VERSION = 1
        private val DATABASE_NAME = "home-inventory.db"

        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = AppDatabase(context.applicationContext)
            }

            return instance!!
        }
    }

}