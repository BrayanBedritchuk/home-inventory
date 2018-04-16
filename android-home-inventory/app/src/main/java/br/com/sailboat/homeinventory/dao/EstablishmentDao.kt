package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite

class EstablishmentDao(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) = EstablishmentDao(AppDatabase.getInstance(context))
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE Establishment ( ")
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        sb.append(" name TEXT NOT NULL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')) ")
        sb.append(" ); ")

        return sb.toString()
    }
}