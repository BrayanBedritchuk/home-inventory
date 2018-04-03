package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite

class ProductCategoryDao(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) = ProductCategoryDao(AppDatabase.getInstance(context))
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE ProductCategory ( ")
        sb.append(" productId INTEGER NOT NULL, ")
        sb.append(" categoryId INTEGER NOT NULL, ")
        sb.append(" created TIMESTAMP DEFAULT CURRENT_TIMESTAMP, ")
        sb.append(" PRIMARY KEY(productId, categoryId), ")
        sb.append(" FOREIGN KEY(productId) REFERENCES Product(id), ")
        sb.append(" FOREIGN KEY(categoryId) REFERENCES Category(id) ")
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