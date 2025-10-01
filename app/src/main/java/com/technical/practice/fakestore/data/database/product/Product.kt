package com.technical.practice.fakestore.data.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
//import com.technical.practice.fakestore.data.database.category.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity (
    tableName = "product"
)
data class Product (
    @PrimaryKey()
    @ColumnInfo(name = "id_product")
    val idProduct: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    @Transient @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false
)
