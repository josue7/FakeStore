package com.technical.practice.fakestore.data.database.favorite

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.technical.practice.fakestore.data.database.product.Product

@Entity(
    tableName = "favorite",
    foreignKeys = [
        ForeignKey (
            entity = Product::class,
            parentColumns = ["id_product"],
            childColumns = ["product_id"],
            onDelete = ForeignKey.CASCADE

        )
    ]
)
data class Favorite(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_favorite") val idFavorite: Int = 0,
    @ColumnInfo("product_id") val productID: Int
)
