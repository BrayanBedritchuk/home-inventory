package br.com.sailboat.homeinventory.data.table

class ProductQuantitySQLiteTable : SQLiteTable {

    override val createTableStatement = StringBuilder()
        .append(" CREATE TABLE ProductQuantity ( ")
        .append(" id INTEGER PRIMARY KEY AUTOINCREMENT, ")
        .append(" productId INTEGER NOT NULL, ")
        .append(" quantity INTEGER NOT NULL, ")
        .append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" FOREIGN KEY(productId) REFERENCES Product(id) ")
        .append(" ); ")
        .toString()

}