package com.technical.practice.fakestore.data.repository

import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.data.database.product.ProductDao
import kotlinx.coroutines.flow.Flow

class OfflineProductRepository (private val  productDao: ProductDao): ProductRepositoryLocal {
    override fun getProductsByCategory(category: String): Flow<List<Product>>  = productDao.getProductByCategory(category)

    override fun getProductById(productId: Int): Flow<Product> = productDao.getProductById(productId)

    override fun getFavoriteProducts(): Flow<List<Product>> = productDao.getFavoriteProducts()

    override suspend fun updateFavoriteStatus(product: Product) = productDao.updateFavoriteStatus(product.idProduct)

    override suspend fun insert(product: Product) = productDao.insert(product)
}