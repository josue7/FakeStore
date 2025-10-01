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
            price = 299.99,
            description = "Smartphone con cámara triple y pantalla AMOLED",
            category = "ypypyoy",
            image = "https://example.com/images/smartphone_xyz.png"
        ),
        Product(
            idProduct = 2,
            title = "Audífonos Bluetooth",
            price = 59.99,
            description = "Auriculares inalámbricos con cancelación de ruido",
            category = "tuututu",
            image = "https://example.com/images/auriculares_bt.png"
        ),
        Product(
            idProduct = 3,
            title = "Libro de Kotlin",
            price = 24.99,
            description = "Guía práctica para programar en Kotlin",
            category = "gfgfgh",
            image = "https://example.com/images/libro_kotlin.png"
        ),
        Product(
            idProduct = 4,
            title = "Cámara deportiva",
            price = 149.99,
            description = "Cámara resistente al agua con grabación 4K",
            category = "tryry",
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