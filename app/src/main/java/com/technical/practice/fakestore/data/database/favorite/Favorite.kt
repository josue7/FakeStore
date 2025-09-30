package com.technical.practice.fakestore.data.database.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class Favorite(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_favorite") val idFavorite: Int = 0,
    @ColumnInfo("product_id") val productID: Int
)
