package com.technical.practice.fakestore.data.database.category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category")
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id_category") val idCategory: Int = 0,
    val category: String
)
