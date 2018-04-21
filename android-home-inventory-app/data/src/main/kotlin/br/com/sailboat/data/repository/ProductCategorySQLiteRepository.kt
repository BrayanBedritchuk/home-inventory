package br.com.sailboat.homeinventory.data.repository

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.data.AppDatabase

class ProductCategorySQLiteRepository(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) =
            ProductCategorySQLiteRepository(
                AppDatabase.getInstance(context)
            )
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE ProductCategory ( ")
        sb.append(" productId INTEGER NOT NULL, ")
        sb.append(" categoryId INTEGER NOT NULL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" PRIMARY KEY(productId, categoryId), ")
        sb.append(" FOREIGN KEY(productId) REFERENCES Product(id), ")
        sb.append(" FOREIGN KEY(categoryId) REFERENCES CategoryData(id) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun insert(productId: Long, categoryId: Long) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO ProductCategory ")
        sql.append(" (productId, categoryId) ")
        sql.append(" VALUES (?, ?); ")

        val stmt = compileStatement(sql.toString())
        stmt.bindLong(1, productId)
        stmt.bindLong(2, categoryId)

        insert(stmt)
    }

}