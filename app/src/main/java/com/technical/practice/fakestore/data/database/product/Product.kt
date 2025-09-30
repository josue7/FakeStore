package com.technical.practice.fakestore.data.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName = "product")
data class Product (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_product") val idProduct: Int = 1,
    val title: String,
    val price: Float,
     val description: String,
    @ColumnInfo(name = "category_id") val categoryID: Int,
    val image: String
)
