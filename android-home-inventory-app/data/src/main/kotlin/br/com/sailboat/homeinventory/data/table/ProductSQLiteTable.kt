package br.com.sailboat.homeinventory.data.table

class ProductSQLiteTable : SQLiteTable {

    override val createTableStatement = StringBuilder()
        .append(" CREATE TABLE Product ( ")
        .append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        .append(" name TEXT NOT NULL, ")
        .append(" quantity INTEGER DEFAULT 0, ")
        .append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')) ")
        .append(" ); ")
        .toString()

}