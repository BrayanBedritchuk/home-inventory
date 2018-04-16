package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.homeinventory.model.Shopping

class ShoppingDao(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) = ShoppingDao(AppDatabase.getInstance(context))
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE Shopping ( ")
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        sb.append(" establishmentId INTEGER, ")
        sb.append(" dateTime DATETIME, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" FOREIGN KEY(establishmentId) REFERENCES Establishment(id) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun insert(shopping: Shopping) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO Shopping ")
        sql.append(" (establishmentId, dateTime) ")
        sql.append(" VALUES (?, ?); ")

        val stmt = compileStatement(sql.toString())

        bindLongOrNull(stmt, 1, shopping.establishmentId)
        stmt.bindString(2, getCalendarToBind(shopping.dateTime))

        val id = insert(stmt)
        shopping.id = id
    }

    fun update(shopping: Shopping) {


    }
}