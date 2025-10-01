package com.technical.practice.fakestore.ui.product

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technical.practice.fakestore.R

@Composable
fun ListProducts () {
    //Se muestra una lista de cards con los productos
}

@Composable
fun CardProduct (
    title: String,
    price: Double,
    image: String, // URL de la imagen
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp)
            .clickable { /* Podrías manejar un click en toda la tarjeta aquí si es necesario */ },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp) // Añade una ligera elevación
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = title, // Descripción para accesibilidad
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp), // Ajusta la altura según tus necesidades
                contentScale = ContentScale.Crop // Escala la imagen para que llene el espacio
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // Padding interno para el texto y el botón
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium, // Un estilo de título adecuado
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "$${String.format("%.2f", price)}", // Formatea el precio a 2 decimales
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                IconButton(
                    onClick = onFavoriteClick,
                    modifier = Modifier.align(Alignment.End) // Alinea el botón a la derecha
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

@Composable
@Preview
fun CardProductPreview () {
    MaterialTheme {
        CardProduct(
            title = "Producto de Ejemplo",
            price = 29.99,
             image = "...", // Ya no es necesario si la imagen es fija desde drawable
            isFavorite = true,
            onFavoriteClick = { }
        )
    }
}

@Composable
@Preview
fun CardProductNotPreview () {
    MaterialTheme {
        CardProduct(
            title = "de Ejemplo",
            price = 43.99,
            image = "...", // Ya no es necesario si la imagen es fija desde drawable
            isFavorite = false,
            onFavoriteClick = { }
        )
    }
}