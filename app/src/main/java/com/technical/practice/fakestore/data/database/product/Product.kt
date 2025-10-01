package com.technical.practice.fakestore.data.database.product

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.technical.practice.fakestore.data.database.category.Category
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
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
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_product")
    @SerialName(value = "id")
    val idProduct: Int = 1,
    @SerialName(value = "title") val title: String,
    @SerialName (value = "price") val price: Float,
    @SerialName(value = "description") val description: String,
    @ColumnInfo(name = "category_id") @SerialName(value = "category") val categoryID: Int,
    @SerialName(value = "image") val image: String,
    @Transient @ColumnInfo(name = "is_favorite") val isFavorite: Boolean = false
)
