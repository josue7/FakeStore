package com.technical.practice.fakestore.data.apiservice

import com.technical.practice.fakestore.data.database.product.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApiService {
    @GET ("products")
    suspend fun getProducts(): List<Product>

    @GET ("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

    @GET ("products/categories")
    suspend fun getCategories(): List<String>

}