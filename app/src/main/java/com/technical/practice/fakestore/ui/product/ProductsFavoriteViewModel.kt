package com.technical.practice.fakestore.ui.product

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.technical.practice.fakestore.data.repository.ProductRepositoryLocal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsFavoriteViewModel (
    private val productRepositoryLocal: ProductRepositoryLocal
): ViewModel() {

    fun onFavoriteToggle (productId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            productRepositoryLocal.updateFavoriteStatus(productId)
        }
    }
}