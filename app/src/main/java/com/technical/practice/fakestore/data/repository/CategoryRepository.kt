package com.technical.practice.fakestore.data.repository

import com.technical.practice.fakestore.data.database.category.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    fun getCategories(): Flow <List<Category> >
    suspend fun insert(category: Category)

}