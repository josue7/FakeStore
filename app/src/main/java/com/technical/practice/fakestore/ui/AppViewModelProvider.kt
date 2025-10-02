package com.technical.practice.fakestore.ui

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.technical.practice.fakestore.FakeStoreApplication
import com.technical.practice.fakestore.data.apiservice.FakeStoreApiService
import com.technical.practice.fakestore.ui.home.HomeViewModel
import com.technical.practice.fakestore.ui.product.ProductViewModel
import com.technical.practice.fakestore.ui.product.ProductsFavoriteViewModel

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                productRepositoryLocal = this.fakeStoreApplication().container.productRepositoryLocal
            )
        }

        initializer {
            ProductViewModel(
                productRepositoryLocal = this.fakeStoreApplication().container.productRepositoryLocal,
                savedStateHandle = createSavedStateHandle()
            )
        }

        initializer {
            ProductsFavoriteViewModel (
                productRepositoryLocal = this.fakeStoreApplication().container.productRepositoryLocal
            )
        }
    }

}

fun CreationExtras.fakeStoreApplication(): FakeStoreApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FakeStoreApplication)