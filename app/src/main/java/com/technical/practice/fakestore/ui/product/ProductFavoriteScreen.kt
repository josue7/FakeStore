package com.technical.practice.fakestore.ui.product

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.ui.AppViewModelProvider
import com.technical.practice.fakestore.ui.FakeStoreAppBar
import com.technical.practice.fakestore.ui.navigation.NavigationDestination
import com.technical.practice.fakestore.ui.theme.FakeStoreTheme

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