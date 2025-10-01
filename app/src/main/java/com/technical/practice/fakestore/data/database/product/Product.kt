package com.technical.practice.fakestore.data.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (
    tableName = "product"
)
data class Product (
    @PrimaryKey @ColumnInfo(name = "id_product") val idProduct: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "price") val price: Double,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "category") val category: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false
)
