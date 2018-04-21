package br.com.sailboat.homeinventory.data.table

class ShoppingSQLiteTable : SQLiteTable {

    override val createTableStatement = StringBuilder()
        .append(" CREATE TABLE ShoppingData ( ")
        .append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        .append(" establishmentId INTEGER, ")
        .append(" dateTime DATETIME, ")
        .append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" FOREIGN KEY(establishmentId) REFERENCES Establishment(id) ")
        .append(" ); ")
        .toString()

}