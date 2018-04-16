package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.canoe.exception.EntityNotFoundException
import br.com.sailboat.canoe.helper.StringHelper
import br.com.sailboat.homeinventory.dao.filter.ProductFilter
import br.com.sailboat.homeinventory.model.Product
import java.util.*


class ProductDao(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) = ProductDao(AppDatabase.getInstance(context))
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE Product ( ")
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        sb.append(" name TEXT NOT NULL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun getProductById(id: Long): Product {
        val cursor = performQuery("SELECT * FROM Product WHERE id = $id")

        if (cursor.moveToNext()) {
            val product = buildFromCursor(cursor)
            cursor.close()
            return product
        }

        throw EntityNotFoundException()
    }

    fun getAllProducts(filter: ProductFilter): List<Product> {
        val sb = StringBuilder()
        sb.append(" SELECT Product.* FROM Product ")
        sb.append(" WHERE 1=1 ")

        if (StringHelper.isNotEmpty(filter.searchText)) {
            sb.append(" AND Product.name LIKE ? ")
        }

        return ArrayList(getProductsFromQuery(sb.toString(), filter))
    }

    fun insert(product: Product) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO Product ")
        sql.append(" (name) ")
        sql.append(" VALUES (?); ")

        val stmt = compileStatement(sql.toString())
        stmt.bindString(1, product.name)

        val id = insert(stmt)
        product.id = id
    }

    @Throws(Exception::class)
    fun update(product: Product) {
        val sql = StringBuilder()
        sql.append(" UPDATE Product SET ")
        sql.append(" name = ?, lastModified = datetime('now') ")
        sql.append(" WHERE id = ? ")

        val statement = compileStatement(sql.toString())
        statement.bindString(1, product.name)
        statement.bindLong(2, product.id)

        update(statement)
    }

    fun delete(id: Long) {
        val statement = compileStatement("DELETE FROM Product WHERE id = ?")
        statement.bindLong(1, id)

        delete(statement)
    }

    @Throws(Exception::class)
    private fun buildFromCursor(cursor: Cursor): Product {
        val id = getLong(cursor, "id")
        val name = getString(cursor, "name")

        return Product(id, name)
    }

    @Throws(Exception::class)
    private fun getProductsFromQuery(query: String, filter: ProductFilter): List<Product> {
        val cursor = performQuery(query, filter)
        val produtcs = ArrayList<Product>()

        while (cursor.moveToNext()) {
            val product = buildFromCursor(cursor)
            produtcs.add(product)
        }

        cursor.close()

        return produtcs
    }

}
