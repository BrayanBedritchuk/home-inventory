package br.com.sailboat.homeinventory.data.repository

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite
import br.com.sailboat.homeinventory.data.AppDatabase
import br.com.sailboat.homeinventory.data.model.ShoppingData

class ShoppingSQLiteRepository(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) =
            ShoppingSQLiteRepository(
                AppDatabase.getInstance(context)
            )
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE ShoppingData ( ")
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        sb.append(" establishmentId INTEGER, ")
        sb.append(" dateTime DATETIME, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" FOREIGN KEY(establishmentId) REFERENCES Establishment(id) ")
        sb.append(" ); ")

        return sb.toString()
    }

    fun insert(shoppingData: ShoppingData) {
        val sql = StringBuilder()

        sql.append(" INSERT INTO ShoppingData ")
        sql.append(" (establishmentId, dateTime) ")
        sql.append(" VALUES (?, ?); ")

        val stmt = compileStatement(sql.toString())

        bindLongOrNull(stmt, 1, shoppingData.establishmentId)
        stmt.bindString(2, getCalendarToBind(shoppingData.dateTime))

        val id = insert(stmt)
        shoppingData.id = id
    }

    fun update(shoppingData: ShoppingData) {


    }
}