package com.technical.practice.fakestore.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.ui.AppViewModelProvider
import com.technical.practice.fakestore.ui.FakeStoreAppBar
import com.technical.practice.fakestore.ui.home.HomeBody
import com.technical.practice.fakestore.ui.navigation.NavigationDestination
import com.technical.practice.fakestore.ui.theme.FakeStoreTheme
import java.util.Locale

object ProductDestination: NavigationDestination {
    override val route = "product"
    override val titleRes = R.string.title_screen_product
    const val PRODUCRCATEGORY = "category"
    val routeWithArgs = "$route/{$PRODUCRCATEGORY}"
}

@Composable
fun ProductScreen (
    viewModel: ProductViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToView: (Int) -> Unit = {},
    onBackScreen: () -> Unit = {}
) {
    FakeStoreTheme {
        Scaffold (
            topBar = {
                FakeStoreAppBar(
                    title = stringResource(ProductDestination.titleRes),
                    modifier = Modifier,
                    canNavigationBack = true,
                    navigateUp = onBackScreen
                )
            }
        ) { innerPadding ->

            ListProducts (
                products = viewModel.products.collectAsState().value,
                onFavoriteToggle = { productId ->
                    viewModel.onFavoriteToggle(productId)
                },
                onProductClick = { navigateToView(it) },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Composable
fun ListProducts (
    products: List<Product>,
    onFavoriteToggle: (productId: Int) -> Unit,
    onProductClick: (productId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    if (products.isEmpty()) {
        Text(
            text = stringResource(R.string.text_empty_list),
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
    ) {
        items(
            items = products,
            key = { product -> product.idProduct }
        ) { product ->
            CardProduct(
                title = product.title,
                price = product.price,
                isFavorite = product.isFavorite,
                image = product.image,
                onFavoriteClick = { onFavoriteToggle(product.idProduct) },
                modifier = Modifier.clickable { onProductClick(product.idProduct) }
            )
        }
    }
}

@Composable
fun CardProduct (
    title: String,
    price: Double,
    image: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { /* TODO */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            Image(
                painter = rememberAsyncImagePainter(model = image),
//                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = stringResource(R.string.text_price, formatPrice(price)),
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.align(Alignment.End)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                        contentDescription = if (isFavorite) "Quitar de favoritos" else "Agregar a favoritos",
                        tint = if (isFavorite) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }

}

fun formatPrice (mount: Double) = String.format(Locale.US, "%.2f", mount)

@Composable
@Preview
fun ProductView () {
    val productosEjemplo = listOf(
        Product(
            idProduct = 1,
            title = "Smartphone XYZ",
            price = 299.99,
            description = "Smartphone con cámara triple y pantalla AMOLED",
            category = "ñoñololo",
            image = "https://example.com/images/smartphone_xyz.png"
        ),
        Product(
            idProduct = 2,
            title = "Audífonos Bluetooth",
            price = 59.99,
            description = "Auriculares inalámbricos con cancelación de ruido",
            category = "plñl",
            image = "https://example.com/images/auriculares_bt.png"
        ),
        Product(
            idProduct = 3,
            title = "Libro de Kotlin",
            price = 24.99,
            description = "Guía práctica para programar en Kotlin",
            category = "kfif",
            image = "https://example.com/images/libro_kotlin.png"
        ),
        Product(
            idProduct = 4,
            title = "Cámara deportiva",
            price = 149.99,
            description = "Cámara resistente al agua con grabación 4K",
            category = "343",
            image = "https://example.com/images/camara_deportiva.png"
        )
    )

    ListProducts (
        products = productosEjemplo,
        onFavoriteToggle = { },
        onProductClick = { }
    )
}