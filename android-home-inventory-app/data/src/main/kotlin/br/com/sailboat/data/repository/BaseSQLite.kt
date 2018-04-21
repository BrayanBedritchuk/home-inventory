package br.com.sailboat.homeinventory.data.repository

import android.database.Cursor
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteStatement
import br.com.sailboat.homeinventory.core.Filter

abstract class BaseSQLite(private val database: SQLiteOpenHelper) {

    protected fun insert(statement: SQLiteStatement): Long {
        try {
            beginTransactionNonExclusive()
            val rowId = statement.executeInsert()
            statement.clearBindings()
            setTransactionSuccessful()
            return rowId
        } finally {
            endTransaction()
        }
    }

    protected fun update(statement: SQLiteStatement) {
        executeUpdateOrDelete(statement)
    }

    protected fun delete(statement: SQLiteStatement) {
        executeUpdateOrDelete(statement)
    }

    protected fun executeQuery(query: String) = executeQuery(query, null)

    protected fun executeQuery(query: String, filter: Filter?): Cursor {
        return if (filter == null || filter.searchQuery.isNullOrBlank()) {
            database.readableDatabase.rawQuery(query, null)
        } else {
            val args = arrayOf("%${filter.searchQuery.trim()}%")
            database.readableDatabase.rawQuery(query, args)
        }
    }

    fun beginTransactionNonExclusive() {
        database.writableDatabase.beginTransactionNonExclusive()
    }

    fun setTransactionSuccessful() {
        database.writableDatabase.setTransactionSuccessful()
    }

    fun endTransaction() {
        database.writableDatabase.endTransaction()
    }

    fun compileStatement(statement: String) =
        database.writableDatabase.compileStatement(statement)

    private fun executeUpdateOrDelete(statement: SQLiteStatement) {
        try {
            beginTransactionNonExclusive()
            statement.executeUpdateDelete()
            statement.clearBindings()
            setTransactionSuccessful()
        } finally {
            endTransaction()
        }
    }

}