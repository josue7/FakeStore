package com.technical.practice.fakestore.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.technical.practice.fakestore.data.apiservice.ProductAPI
import com.technical.practice.fakestore.data.repository.ProductRepositoryNet
import kotlinx.coroutines.launch
import okio.IOException

sealed interface ProductUiState {
    data class Success(val products: List<ProductAPI>) : ProductUiState
    object Error : ProductUiState
    object Loading : ProductUiState
}

class HomeViewModel (
    private val productRepositoryNet: ProductRepositoryNet
): ViewModel () {
    var productUiState: ProductUiState by mutableStateOf(ProductUiState.Loading)
        private set

    private lateinit var products: List<ProductAPI>
    private lateinit var categories: List<String>

    init {
        getProducts()
    }

    fun getProducts () {
        viewModelScope.launch {
            productUiState = ProductUiState.Loading
            productUiState = try {
                ProductUiState.Success(productRepositoryNet.getProducts())
            } catch (e: IOException) {
                ProductUiState.Error
            } catch (e: HttpException) {
                ProductUiState.Error
            }
        }
    }

}