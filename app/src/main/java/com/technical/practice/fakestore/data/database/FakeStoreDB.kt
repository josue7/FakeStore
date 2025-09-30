package com.technical.practice.fakestore.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.technical.practice.fakestore.data.database.category.Category
import com.technical.practice.fakestore.data.database.favorite.Favorite
import com.technical.practice.fakestore.data.database.product.Product

@Database ( entities = [Category::class, Product::class, Favorite::class], version = 1, exportSchema = false )
abstract class FakeStoreDB: RoomDatabase() {

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