package br.com.sailboat.homeinventory.data

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity(tableName = "Product")
data class ProductData(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @NonNull val name: String,
    @NonNull var quantity: Int
)