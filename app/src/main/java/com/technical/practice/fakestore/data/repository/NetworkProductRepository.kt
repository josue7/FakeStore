package com.technical.practice.fakestore.data.repository

import com.technical.practice.fakestore.data.apiservice.FakeStoreApiService
import com.technical.practice.fakestore.data.apiservice.ProductAPI
import com.technical.practice.fakestore.data.database.product.Product

class NetworkProductRepository (private val fakeStoreApiService: FakeStoreApiService): ProductRepositoryNet  {
    override suspend fun getProducts(): List<ProductAPI> = fakeStoreApiService.getProducts()

}