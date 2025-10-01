package com.technical.practice.fakestore.data.apiservice

//import com.technical.practice.fakestore.data.database.category.Category
import com.technical.practice.fakestore.data.database.product.Product
import retrofit2.http.GET
import retrofit2.http.Path

interface FakeStoreApiService {
    @GET ("products")
    suspend fun getProducts(): List<ProductAPI>

    @GET ("products/{id}")
    suspend fun getProductById(@Path("id") id: Int): Product

//    @GET ("products/categories")
//    suspend fun getCategories(): List<Category>

}