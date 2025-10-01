package com.technical.practice.fakestore.ui.product

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.ui.navigation.NavigationDestination

object ProductFavoriteDestination: NavigationDestination {
    override val route = "product_favorite"
    override val titleRes = R.string.tab_title_favorite
}

@Composable
fun ProductFavoriteScreen (
    navigateToView: (Int) -> Unit = {}
) {
    val productosEjemplo = listOf(
        Product(
            idProduct = 1,
            title = "Smartphone XYZ",
            price = 299.99f,
            description = "Smartphone con cámara triple y pantalla AMOLED",
            categoryID = 1,
            image = "https://example.com/images/smartphone_xyz.png"
        ),
        Product(
            idProduct = 2,
            title = "Audífonos Bluetooth",
            price = 59.99f,
            description = "Auriculares inalámbricos con cancelación de ruido",
            categoryID = 2,
            image = "https://example.com/images/auriculares_bt.png"
        ),
        Product(
            idProduct = 3,
            title = "Libro de Kotlin",
            price = 24.99f,
            description = "Guía práctica para programar en Kotlin",
            categoryID = 3,
            image = "https://example.com/images/libro_kotlin.png"
        ),
        Product(
            idProduct = 4,
            title = "Cámara deportiva",
            price = 149.99f,
            description = "Cámara resistente al agua con grabación 4K",
            categoryID = 4,
            image = "https://example.com/images/camara_deportiva.png"
        )
    )
    ListProducts (
        products = productosEjemplo,
        onFavoriteToggle ={},
        onProductClick = {}
    )
}

@Composable
@Preview
fun ProductFavoriteScreenPreview () {

}