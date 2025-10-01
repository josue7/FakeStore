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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
import java.util.Locale

@Composable
fun ListProducts (
    products: List<Product>,
    onFavoriteToggle: (productId: Int) -> Unit,
    onProductClick: (productId: Int) -> Unit
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
        horizontalArrangement = Arrangement.spacedBy(8.dp)
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
    price: Float,
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
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
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

fun formatPrice (mount: Float) = String.format(Locale.US, "%.2f", mount)

//@Composable
//@Preview
//fun CardProductPreview () {
//    MaterialTheme {
//        CardProduct(
//            title = "Producto de Ejemplo",
//            price = 29.995f,
//             image = "...",
//            isFavorite = true,
//            onFavoriteClick = { }
//        )
//    }
//}
//
//@Composable
//@Preview
//fun CardProductNotPreview () {
//    MaterialTheme {
//        CardProduct(
//            title = "de Ejemplo",
//            price = 43.993f,
//            image = "...",
//            isFavorite = false,
//            onFavoriteClick = { }
//        )
//    }
//}

@Composable
@Preview
fun ProductView () {
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
        onFavoriteToggle = { },
        onProductClick = { }
    )
}