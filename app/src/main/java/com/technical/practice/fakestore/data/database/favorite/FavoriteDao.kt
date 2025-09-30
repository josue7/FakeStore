package com.technical.practice.fakestore.data.database.favorite

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite INNER JOIN product ON product_id = id_product")
    fun getAllFavorites(): Flow< List<Favorite> >

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavorite(favorite: Favorite)

    @Query("DELETE FROM favorite WHERE product_id = :productId")
    suspend fun removeFavoriteById(productId: Int): Int
}