package com.technical.practice.fakestore.data.database.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "category")
data class Category(
    @Transient @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_category") val idCategory: Int = 0,
    @SerialName("category") val category: String
)
