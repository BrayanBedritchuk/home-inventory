package br.com.sailboat.homeinventory.data

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import br.com.sailboat.homeinventory.data.dao.ProductDAO

@Database(entities = arrayOf(ProductData::class), version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun productDao(): ProductDAO

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase::class.java, "home-inventory.db").build()
                }
            }
            return INSTANCE!!
        }

    }
}