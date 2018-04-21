package br.com.sailboat.homeinventory.data.table

class ProductCategorySQLiteTable : SQLiteTable {

    override val createTableStatement = StringBuilder()
        .append(" CREATE TABLE ProductCategory ( ")
        .append(" productId INTEGER NOT NULL, ")
        .append(" categoryId INTEGER NOT NULL, ")
        .append(" created DATETIME DEFAULT (datetime('now', 'localtime')), ")
        .append(" PRIMARY KEY(productId, categoryId), ")
        .append(" FOREIGN KEY(productId) REFERENCES Product(id), ")
        .append(" FOREIGN KEY(categoryId) REFERENCES CategoryData(id) ")
        .append(" ); ")
        .toString()

}