package com.technical.practice.fakestore.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technical.practice.fakestore.data.database.category.Category
import com.technical.practice.fakestore.data.database.category.CategoryDao
import com.technical.practice.fakestore.data.database.favorite.Favorite
import com.technical.practice.fakestore.data.database.favorite.FavoriteDao
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.data.database.product.ProductDao

@Database ( entities = [Category::class, Product::class, Favorite::class], version = 1, exportSchema = false )
abstract class FakeStoreDB: RoomDatabase() {
    abstract fun categoryDao(): CategoryDao
    abstract fun productDao(): ProductDao
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FakeStoreDB? = null

        fun getDatabase(context: Context): FakeStoreDB {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(context, FakeStoreDB::class.java,"fake_store_db")
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}