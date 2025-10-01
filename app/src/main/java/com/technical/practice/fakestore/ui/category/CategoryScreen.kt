package com.technical.practice.fakestore.ui.category

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technical.practice.fakestore.data.database.category.Category

@Composable
fun ListCategories (categories: List<Category>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(categories) {
            CardCategory(it.category, modifier = Modifier.padding(4.dp))
        }
    }
}

@Composable
private fun CardCategory (category: String, modifier: Modifier = Modifier) {
    Card (
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = category,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 12.dp)

            )
        }
    }
}

@Composable
@Preview
fun CategoryScreenPreview () {
    val categoriasEjemplo = listOf(
        Category(idCategory = 1, category = "Tecnología"),
        Category(idCategory = 2, category = "Deportes"),
        Category(idCategory = 3, category = "Música"),
        Category(idCategory = 4, category = "Cine"),
        Category(idCategory = 5, category = "Literatura")
    )
    ListCategories(categoriasEjemplo)
}