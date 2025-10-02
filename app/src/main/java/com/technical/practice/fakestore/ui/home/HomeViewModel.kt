package com.technical.practice.fakestore.ui.home

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import coil.network.HttpException
import com.technical.practice.fakestore.data.apiservice.ProductAPI
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.data.repository.ProductRepositoryLocal
import com.technical.practice.fakestore.data.repository.ProductRepositoryNet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.ignoreIoExceptions
import okio.IOException

sealed interface HomeUiState {
    data class Success(val products: List<Product>) : HomeUiState
    object Error : HomeUiState
    object Loading : HomeUiState
}

class HomeViewModel (
    private val productRepositoryNet: ProductRepositoryNet,
    private val productRepositoryLocal: ProductRepositoryLocal
): ViewModel () {

    private val _uiState = MutableStateFlow<HomeUiState>(HomeUiState.Loading)
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    private val _categories = MutableStateFlow<List<String>>(emptyList())
    val categories: StateFlow<List<String>> = _categories.asStateFlow()

    private var hasInitialDataLoaded = false

    init {
        loadInitialData()
    }

    private fun loadInitialData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                observeProducts()
                if (!hasInitialDataLoaded) {
                    productRepositoryLocal.refreshProducts()
                }
            } catch (e: Exception) {
                Log.e("Variable error", "Error al refrescar productos", e)
                _uiState.value = HomeUiState.Error
            }
        }

    }

    private fun observeProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                productRepositoryLocal.getAllProducts()
                    .catch {
                        Log.e("Variable error", "Error al obtener productos", it)
                        _uiState.value = HomeUiState.Error
                    }
                    .collect { productsList ->
                        Log.d("Variable ms", "Productos recibidos de BD: ${productsList.size}")

                        if (productsList.isNotEmpty()) {
                            val currentCategories = productsList.map { it.category }.toSet().toList()

                            withContext(Dispatchers.Main) {
                                _categories.value = currentCategories
                            }

                            _uiState.value = HomeUiState.Success(productsList)
                            hasInitialDataLoaded = true
                        } else {
                            _uiState.value = HomeUiState.Loading
                        }
                    }
            } catch (e: Exception) {
                Log.e("Variable error", "Error al obtener productos", e)
                _uiState.value = HomeUiState.Error
            }

        }

    }

    fun refreshData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = HomeUiState.Loading
                productRepositoryLocal.refreshProducts()
            } catch (e: Exception) {
                Log.e("Variable error", "Error en refresh manual", e)
                _uiState.value = HomeUiState.Error
            }
        }
    }

//    fun getProducts () {
//        viewModelScope.launch {
//            productUiState = ProductUiState.Loading
//            productUiState = try {
//                ProductUiState.Success(productRepositoryNet.getProducts())
//            } catch (e: IOException) {
//                ProductUiState.Error
//            } catch (e: HttpException) {
//                ProductUiState.Error
//            }
//        }
//    }

//    private fun observeProducts () {
//        viewModelScope.launch (Dispatchers.IO) {
//            productRepositoryLocal.getAllProducts()
//                .catch { exception ->
//                    Log.e("Variable error", "Error al obtener productos", exception)
//                    _uiState.value = HomeUiState.Error
//                }
//                .collect { productsList ->
//                    Log.d("Variable ms", "Productos recibidos de BD: ${productsList.toString()}")
//                    val currentCategories = productsList.map { it.category }.toSet().toList()
//                    withContext(Dispatchers.Main) {
//                        _categories.value = currentCategories
//                    }
//
//                    if (productsList.isEmpty()) {
//                        _uiState.value = HomeUiState.Loading
//                    } else {
//                        _uiState.value = HomeUiState.Success(productsList)
//                    }
//
//                }
//        }
//    }

    private fun observeRefresh() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (_uiState.value) {
                    is HomeUiState.Loading, is HomeUiState.Success -> {
                        Log.d("Variable refresh", "Iniciando refresh desde observeRefresh")
                        productRepositoryLocal.refreshProducts()
                    }
                    else -> {
                        Log.d("Variable ref", "Refresh no necesario, estado actual: ${_uiState.value}")
                    }
                }
            } catch (e: Exception) {
                Log.e("Variable error", "Error al refrescar productos", e)
                _uiState.value = HomeUiState.Error
            }
//            if (_uiState.value is HomeUiState.Loading || _uiState.value is HomeUiState.Error) {
//                productRepositoryLocal.refreshProducts()
//            }
        }

    }

}