package com.technical.practice.fakestore.data.database.category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM category ORDER BY category ASC")
    fun getAllCategories(): Flow< List<Category> >

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: Category)

}