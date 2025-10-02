package com.technical.practice.fakestore.ui.product

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.ui.AppViewModelProvider
import com.technical.practice.fakestore.ui.navigation.NavigationDestination

object ProductFavoriteDestination: NavigationDestination {
    override val route = "product_favorite"
    override val titleRes = R.string.tab_title_favorite
}

@Composable
fun ProductFavoriteScreen (
    listProducts: List<Product> = emptyList(),
    viewModel: ProductsFavoriteViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToView: (Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    ListProducts (
        products = listProducts,
        onFavoriteToggle ={ productId ->
            viewModel.onFavoriteToggle(productId)
        },
        onProductClick = {},
        modifier = modifier
    )
}

@Composable
@Preview
fun ProductFavoriteScreenPreview () {

}