package br.com.sailboat.homeinventory.data.repository

import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.exception.EntityNotFoundException
import br.com.sailboat.canoe.helper.StringHelper
import br.com.sailboat.homeinventory.core.Filter
import br.com.sailboat.homeinventory.data.model.ProductData
import java.util.*


class ProductSQLite(database: SQLiteOpenHelper) : BaseSQLite(database) {

    fun getProductById(id: Long): ProductData {
        val cursor = executeQuery("SELECT * FROM Product WHERE id = $id")

        if (cursor.moveToNext()) {
            val product = buildFromCursor(cursor)
            cursor.close()
            return product
        }

        throw EntityNotFoundException()
    }

    fun getAllProducts(filter: Filter): List<ProductData> {
        val sb = StringBuilder()
        sb.append(" SELECT Product.* FROM Product ")
        sb.append(" WHERE 1=1 ")

        if (StringHelper.isNotEmpty(filter.searchQuery)) {
            sb.append(" AND Product.name LIKE ? ")
        }

        return ArrayList(getProductsFromQuery(sb.toString(), filter))
    }

    fun getCurrentQuantity(productId: Long): Int {
        val sb = StringBuilder()
        sb.append(" SELECT quantity FROM ProductQuantity ")
        sb.append(" WHERE productId = $productId ")
        sb.append(" ORDER BY created DESC ")

        val cursor = executeQuery(sb.toString())

        if (cursor.moveToNext()) {
            val quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"))
            cursor.close()
            return quantity
        }

        return 0
    }

    fun insert(product: ProductData) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO Product ")
        sql.append(" (name) ")
        sql.append(" VALUES (?); ")

        val stmt = compileStatement(sql.toString())
        stmt.bindString(1, product.name)

        val id = insert(stmt)
        product.id = id
    }

    fun update(product: ProductData) {
        val sql = StringBuilder()
        sql.append(" UPDATE Product SET ")
        sql.append(" name = ?, lastModified = datetime('now') ")
        sql.append(" WHERE id = ? ")

        val statement = compileStatement(sql.toString())
        statement.bindString(1, product.name)
        statement.bindLong(2, product.id)

        update(statement)
    }

    fun delete(productId: Long) {
        val statement = compileStatement("DELETE FROM Product WHERE id = ?")
        statement.bindLong(1, productId)

        delete(statement)
    }

    @Throws(Exception::class)
    private fun buildFromCursor(cursor: Cursor): ProductData {
        val id = cursor.getLong(cursor.getColumnIndexOrThrow("id"))
        val name = cursor.getString(cursor.getColumnIndexOrThrow("name"))

        return ProductData(id, name)
    }

    @Throws(Exception::class)
    private fun getProductsFromQuery(query: String, filter: Filter): List<ProductData> {
        val cursor = executeQuery(query, filter)
        val produtcs = ArrayList<ProductData>()

        while (cursor.moveToNext()) {
            val product = buildFromCursor(cursor)
            produtcs.add(product)
        }

        cursor.close()

        return produtcs
    }

}
