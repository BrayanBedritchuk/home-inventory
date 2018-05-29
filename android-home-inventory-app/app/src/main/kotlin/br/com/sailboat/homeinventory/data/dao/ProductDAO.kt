package br.com.sailboat.homeinventory.data.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.*
import br.com.sailboat.homeinventory.data.ProductData

@Dao
interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(product: ProductData)

    @Delete
    fun delete(product: ProductData)

    @Update
    fun update(product: ProductData)

    @Query("SELECT * FROM Product ORDER BY quantity")
    fun getAll(): DataSource.Factory<Int, ProductData>

    @Query("SELECT * FROM Product ORDER BY quantity")
    fun getProducts(): List<ProductData>

}