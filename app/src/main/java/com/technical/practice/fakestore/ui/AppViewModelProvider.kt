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

object AppViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                productRepositoryNet = this.fakeStoreApplication().container.productRepositoryNet,
                productRepositoryLocal = this.fakeStoreApplication().container.productRepositoryLocal
            )
        }

        initializer {
            ProductViewModel(
                productRepositoryLocal = this.fakeStoreApplication().container.productRepositoryLocal,
                savedStateHandle = createSavedStateHandle()
            )
        }
    }

}

fun CreationExtras.fakeStoreApplication(): FakeStoreApplication = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as FakeStoreApplication)