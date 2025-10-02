package com.technical.practice.fakestore.data.repository

import com.technical.practice.fakestore.data.apiservice.ProductAPI
import com.technical.practice.fakestore.data.database.product.Product
import kotlinx.coroutines.flow.Flow

interface ProductRepositoryNet {
    suspend fun getProducts(): List<ProductAPI>
}

interface ProductRepositoryLocal {
    suspend fun getProductsSync(): List<Product>
    fun getAllProducts(): Flow< List<Product> >
    fun getProductsByCategory(categoryId: String): Flow < List<Product> >
    fun getProductById(productId: Int): Flow < Product >
    fun getFavoriteProducts(): Flow < List<Product> >
    suspend fun updateFavoriteStatus(product: Int)
    suspend fun insertProduct(product: List<Product>)
    suspend fun refreshProducts()
}