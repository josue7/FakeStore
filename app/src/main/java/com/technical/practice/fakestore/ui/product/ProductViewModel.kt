package com.technical.practice.fakestore.ui.product

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

class ProductViewModel(
    private val productRepositoryLocal: ProductRepositoryLocal,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val category: String = checkNotNull(savedStateHandle["category"])

    val products: StateFlow<List<Product>> = productRepositoryLocal.getProductsByCategory(categoryId = category)
        .filterNotNull()
        .map {products ->
            products.map {
                Product(
                    idProduct = it.idProduct,
                    title = it.title,
                    price = it.price,
                    description = it.description,
                    category = it.category,
                    image = it.image,
                    isFavorite = it.isFavorite
                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS ),
            initialValue = emptyList<Product>()
        )

    fun onFavoriteToggle (productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepositoryLocal.updateFavoriteStatus(productId)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}