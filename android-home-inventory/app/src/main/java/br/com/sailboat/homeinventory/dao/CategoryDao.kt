package br.com.sailboat.homeinventory.dao

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import br.com.sailboat.canoe.base.BaseSQLite

class CategoryDao(database: SQLiteOpenHelper) : BaseSQLite(database) {

    companion object {
        fun newInstance(context: Context) = CategoryDao(AppDatabase.getInstance(context))
    }

    override fun getCreateTableStatement(): String {
        val sb = StringBuilder()
        sb.append(" CREATE TABLE Category ( ")
        sb.append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        sb.append(" name TEXT NOT NULL, ")
        sb.append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        sb.append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')) ")
        sb.append(" ); ")

        return sb.toString()
    }

}