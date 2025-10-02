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
    listProducts: List<Product> = emptyList(),
    navigateToView: (Int) -> Unit = {}
) {
    ListProducts (
        products = listProducts,
        onFavoriteToggle ={},
        onProductClick = {}
    )
}

@Composable
@Preview
fun ProductFavoriteScreenPreview () {

}