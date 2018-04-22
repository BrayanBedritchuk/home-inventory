package br.com.sailboat.homeinventory.data.table

class ShoppingProductSQLiteTable : SQLiteTable {

    override val createTableStatement = StringBuilder()
        .append(" CREATE TABLE ShoppingProductData ( ")
        .append(" shoppingId INTEGER, ")
        .append(" productId INTEGER, ")
        .append(" quantity INTEGER, ")
        .append(" unitPrice DECIMAL, ")
        .append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" lastModified DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" PRIMARY KEY(shoppingId, productId), ")
        .append(" FOREIGN KEY(shoppingId) REFERENCES ShoppingData(id), ")
        .append(" FOREIGN KEY(productId) REFERENCES Product(id) ")
        .append(" ); ")
        .toString()

}