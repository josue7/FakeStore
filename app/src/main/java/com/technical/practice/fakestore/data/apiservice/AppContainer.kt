package com.technical.practice.fakestore.data.apiservice

import android.content.Context
//import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.technical.practice.fakestore.data.database.FakeStoreDB
import com.technical.practice.fakestore.data.repository.NetworkProductRepository
import com.technical.practice.fakestore.data.repository.OfflineProductRepository
import com.technical.practice.fakestore.data.repository.ProductRepositoryLocal
import com.technical.practice.fakestore.data.repository.ProductRepositoryNet
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val productRepositoryNet: ProductRepositoryNet
    val productRepositoryLocal: ProductRepositoryLocal
//    val categoryRepositoryNet: CategoryRepositoryNet
//    val categoryRepositoryLocal: CategoryRepositoryLocal
}

class AppDataContainer (private val context: Context): AppContainer {
    private val baseUrl = "https://fakestoreapi.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create() )
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: FakeStoreApiService by lazy {
        retrofit.create(FakeStoreApiService::class.java)
    }

    override val productRepositoryNet: ProductRepositoryNet by lazy {
        NetworkProductRepository(retrofitService)
    }

//    override val categoryRepositoryNet: CategoryRepositoryNet by lazy {
//        NetworkCategoryRepository(retrofitService)
//    }

    override val productRepositoryLocal: ProductRepositoryLocal by lazy {
        OfflineProductRepository(FakeStoreDB.getDatabase(context).productDao())
    }

//    override val categoryRepositoryLocal: CategoryRepositoryLocal by lazy {
//        OfflineCategoryRepository(FakeStoreDB.getDatabase(context).categoryDao())
//    }

}