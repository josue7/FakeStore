package com.technical.practice.fakestore.data.database.product

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Query ("SELECT * FROM product WHERE category_id = :productID")
    fun getProductByCategorie (productID: Int): Flow< List<Product> >

    @Query ("SELECT * FROM product WHERE id_product = :productID")
    fun getProductById (productID: Int): Flow<Product>
}