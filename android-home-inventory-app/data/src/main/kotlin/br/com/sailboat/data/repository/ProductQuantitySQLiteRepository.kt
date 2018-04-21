package br.com.sailboat.homeinventory.data.repository

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.data.AppDatabase

class ProductQuantitySQLiteRepository(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) =
            ProductQuantitySQLiteRepository(
                AppDatabase.getInstance(context)
            )
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE ProductQuantity ( ")
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        sb.append(" productId INTEGER NOT NULL, ")
        sb.append(" quantity INTEGER NOT NULL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" FOREIGN KEY(productId) REFERENCES Product(id) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun getCurrentQuantity(productId: Long): Int {
        val sb = StringBuilder()
        sb.append(" SELECT quantity FROM ProductQuantity ")
        sb.append(" WHERE productId = $productId ")
        sb.append(" ORDER BY created DESC ")

        val cursor = performQuery(sb.toString())

        if (cursor.moveToNext()) {
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"))
            cursor.close()
            return quantity
        }

        return 0
    }

    fun insert(productId: Long, quantity: Int) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO ProductQuantity ")
        sql.append(" (productId, quantity) ")
        sql.append(" VALUES (?, ?); ")

        val stmt = compileStatement(sql.toString())
        stmt.bindLong(1, productId)
        stmt.bindLong(2, quantity.toLong())

        insert(stmt)
    }

}