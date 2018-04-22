package br.com.sailboat.homeinventory.data.repository

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.homeinventory.data.AppDatabase
import br.com.sailboat.homeinventory.data.model.ShoppingProductData

class ShoppingProductSQLiteRepository(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) =
            ShoppingProductSQLiteRepository(
                AppDatabase.getInstance(context)
            )
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE ShoppingProductData ( ")
        sb.append(" shoppingId INTEGER, ")
        sb.append(" productId INTEGER, ")
        sb.append(" quantity INTEGER, ")
        sb.append(" unitPrice DECIMAL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" PRIMARY KEY(shoppingId, productId), ")
        sb.append(" FOREIGN KEY(shoppingId) REFERENCES ShoppingData(id), ")
        sb.append(" FOREIGN KEY(productId) REFERENCES Product(id) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun insert(shoppingProductData: ShoppingProductData) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO ShoppingProductData ")
        sql.append(" (shoppingId, productId, quantity, unitPrice) ")
        sql.append(" VALUES (?, ?, ?, ?); ")

        val stmt = compileStatement(sql.toString())

        stmt.bindLong(1, shoppingProductData.shoppingId)
        stmt.bindLong(2, shoppingProductData.productId)
        stmt.bindLong(3, shoppingProductData.quantity.toLong())
        stmt.bindDouble(4, shoppingProductData.unitPrice.toDouble())

        insert(stmt)
    }

}