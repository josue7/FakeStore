package com.technical.practice.fakestore

import android.app.Application
import com.technical.practice.fakestore.data.apiservice.AppContainer
import com.technical.practice.fakestore.data.apiservice.AppDataContainer

class FakeStoreApplication: Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}