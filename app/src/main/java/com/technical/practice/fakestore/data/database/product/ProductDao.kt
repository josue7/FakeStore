package com.technical.practice.fakestore.data.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query ("SELECT * FROM product")
    fun getAllProducts (): Flow< List<Product> >

    @Query ("SELECT * FROM product")
    suspend fun getProductsSync (): List<Product>

    @Query ("SELECT * FROM product WHERE category = :category")
    fun getProductByCategory (category: String): Flow< List<Product> >

    @Query ("SELECT * FROM product WHERE id_product = :productID")
    fun getProductById (productID: Int): Flow<Product>

    @Query ("SELECT * FROM product WHERE id_product IN(:ids)")
    fun getProductIds (ids: List<Int>): List<Product>

    @Query ("SELECT * FROM product WHERE is_favorite = 1")
    fun getFavoriteProducts (): Flow<List<Product>>

    @Update
    suspend fun updateProduct (product: List<Product>)

    @Query ("UPDATE product SET is_favorite = NOT is_favorite WHERE id_product = :productID")
    suspend fun updateFavoriteStatus (productID: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct (product: List<Product>)
}