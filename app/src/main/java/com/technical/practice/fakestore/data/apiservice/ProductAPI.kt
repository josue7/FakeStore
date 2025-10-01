package com.technical.practice.fakestore.data.apiservice

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable

data class ProductAPI (
    @SerialName(value = "id") val idProduct: Int,
    @SerialName(value = "title") val title: String,
    @SerialName (value = "price") val price: Double,
    @SerialName(value = "description") val description: String,
    @SerialName(value = "category") val category: String,
    @SerialName(value = "image") val image: String
)

