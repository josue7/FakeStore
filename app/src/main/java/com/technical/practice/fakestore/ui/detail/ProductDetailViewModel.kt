package com.technical.practice.fakestore.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.data.repository.ProductRepositoryLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProductDetailViewModel (
    private val productRepositoryLocal: ProductRepositoryLocal,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    val productId: Int = checkNotNull(savedStateHandle["productId"])
    val productDetail: StateFlow<Product> = productRepositoryLocal.getProductById(productId)
        .filterNotNull()
        .map { product ->
            Product(
                idProduct = product.idProduct,
                title = product.title,
                price = product.price,
                description = product.description,
                category = product.category,
                image = product.image,
                isFavorite = product.isFavorite
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = empty()
        )

    fun onFavoriteToggle (productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepositoryLocal.updateFavoriteStatus(productId)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
        fun empty(): Product {
            return Product(
                idProduct = 0,
                title = "",
                price = 0.0,
                description = "",
                category = "",
                image = "",
                isFavorite = false
            )
        }
    }
}