package com.technical.practice.fakestore.ui.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
import com.technical.practice.fakestore.ui.product.formatPrice

@Composable
fun ProductDetailScreen(
    product: Product,
    onFavoriteToggle: (productId: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onFavoriteToggle(product.idProduct) },
                shape = MaterialTheme.shapes.medium, // Bordes redondeados para el FAB
                containerColor = MaterialTheme.colorScheme.tertiaryContainer, // Un color distintivo
                contentColor = MaterialTheme.colorScheme.onTertiaryContainer
            ) {
                Icon(
                    imageVector = if (product.isFavorite) Icons.Filled.Favorite else Icons.Filled.FavoriteBorder,
                    contentDescription = if (product.isFavorite) "Quitar de favoritos" else "Agregar a favoritos"
                )
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
        ) {

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                // .height(300.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground),
                    contentDescription = product.title,
                    modifier = Modifier
                        .fillMaxWidth() // Ocupa todo el ancho
                        .height(300.dp)
                        // .aspectRatio(16f / 9f)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 24.dp,
                                bottomEnd = 24.dp
                            )
                        ),
                    contentScale = ContentScale.Crop
                )
            }

            // Sección de Información del Producto
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                // Precio
                Text(
                    text = stringResource(R.string.text_price, formatPrice(product.price)),
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Título
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 12.dp)
                )

                // Etiqueta "Descripción"
                Text(
                    text = "Descripción",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

                // Descripción del Producto
                Text(
                    text = product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                Spacer(modifier = Modifier.height(72.dp))
            }
        }
    }

}
@Composable
@Preview
fun ProductDetailScreenPreview() {
        val sampleDetailProduct = Product(
            idProduct = 101,
            title = "Producto Increíble de Alta Calidad con Nombre Largo",
            price = 199.99,
            description = "Esta es una descripción detallada del producto increíble. " +
                    "Cuenta con materiales de primera calidad y un diseño innovador que " +
                    "seguramente te encantará. Perfecto para cualquier ocasión y " +
                    "diseñado para durar. No te pierdas la oportunidad de adquirir esta maravilla.",
            image = "https",
            category = "djsf",
            isFavorite = false
        )
        MaterialTheme {
            var productForPreview by remember { mutableStateOf(sampleDetailProduct) }

            ProductDetailScreen(
                product = productForPreview,
                onFavoriteToggle = {
                    productForPreview = productForPreview.copy(isFavorite = !productForPreview.isFavorite)
                }
            )
        }
}