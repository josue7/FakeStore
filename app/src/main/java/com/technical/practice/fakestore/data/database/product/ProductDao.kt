package com.technical.practice.fakestore.data.database.product

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query ("SELECT * FROM product WHERE category_id = :productID")
    fun getProductByCategorie (productID: Int): Flow< List<Product> >

    @Query ("SELECT * FROM product WHERE id_product = :productID")
    fun getProductById (productID: Int): Flow<Product>

    @Query ("SELECT * FROM product WHERE is_favorite = 1")
    fun getFavoriteProducts (): Flow<List<Product>>

    @Query ("UPDATE product SET is_favorite = NOT is_favorite WHERE id_product = :productID")
    suspend fun updateFavoriteStatus (productID: Int)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert (product: Product)
}