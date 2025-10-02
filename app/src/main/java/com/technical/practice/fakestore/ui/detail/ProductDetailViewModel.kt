package com.technical.practice.fakestore.ui.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.technical.practice.fakestore.data.repository.ProductRepositoryLocal

class ProductDetailViewModel (
    private val productRepositoryLocal: ProductRepositoryLocal,
    savedStateHandle: SavedStateHandle
): ViewModel() {
}