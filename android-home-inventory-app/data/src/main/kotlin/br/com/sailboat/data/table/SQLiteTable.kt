package br.com.sailboat.homeinventory.data.table

interface SQLiteTable {
    val createTableStatement: String
}