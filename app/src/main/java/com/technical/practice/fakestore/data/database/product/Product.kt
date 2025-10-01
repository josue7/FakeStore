package com.technical.practice.fakestore.data.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.technical.practice.fakestore.data.database.category.Category

@Entity (
    tableName = "product",
    foreignKeys = [
        ForeignKey (
            entity = Category::class,
            parentColumns = ["id_category"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE

        )
    ]
)
data class Product (
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_product") val idProduct: Int = 1,
    val title: String,
    val price: Float,
    val description: String,
    @ColumnInfo(name = "category_id") val categoryID: Int,
    val image: String,
    val isFavorite: Boolean = false
)
