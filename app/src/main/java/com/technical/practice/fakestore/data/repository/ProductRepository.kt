package com.technical.practice.fakestore.data.repository

import com.technical.practice.fakestore.data.database.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getProductsByCategory(categoryId: Int): Flow < List<Product> >
    fun getProductById(productId: Int): Flow < Product >
    fun getFavoriteProducts(): Flow < List<Product> >
    suspend fun updateFavoriteStatus(product: Product)
    suspend fun insert(product: Product)
    suspend fun getProducts(): List<Product>
}