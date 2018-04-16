package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.homeinventory.model.ShoppingProduct

class ShoppingProductDao(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) = ShoppingProductDao(AppDatabase.getInstance(context))
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE ShoppingProduct ( ")
        sb.append(" shoppingId INTEGER, ")
        sb.append(" productId INTEGER, ")
        sb.append(" quantity INTEGER, ")
        sb.append(" unitPrice DECIMAL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" PRIMARY KEY(shoppingId, productId), ")
        sb.append(" FOREIGN KEY(shoppingId) REFERENCES Shopping(id), ")
        sb.append(" FOREIGN KEY(productId) REFERENCES Product(id) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun insert(shoppingProduct: ShoppingProduct) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO ShoppingProduct ")
        sql.append(" (shoppingId, productId, quantity, unitPrice) ")
        sql.append(" VALUES (?, ?, ?, ?); ")

        val stmt = compileStatement(sql.toString())

        stmt.bindLong(1, shoppingProduct.shoppingId)
        stmt.bindLong(2, shoppingProduct.productId)
        stmt.bindLong(3, shoppingProduct.quantity.toLong())
        stmt.bindDouble(4, shoppingProduct.unitPrice.toDouble())

        insert(stmt)
    }

}