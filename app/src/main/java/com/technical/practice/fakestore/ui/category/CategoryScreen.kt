package com.technical.practice.fakestore.ui.category

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.technical.practice.fakestore.R
//import com.technical.practice.fakestore.data.database.category.Category
import com.technical.practice.fakestore.ui.navigation.NavigationDestination

object CategoryDestination: NavigationDestination {
    override val route = "categories"
    override val titleRes = R.string.title_screen_category
}

@Composable
fun CategoryScreen (
    categories: List<String>,
    navigateToView: (String) -> Unit = {}
) {
    ListCategories(
        categories = categories,
        onTransactionClick = { navigateToView(it) },
        modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun ListCategories (
    categories: List<String>,
    onTransactionClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
    ) {
        items(categories, key = { it }) {
            CardCategory(
                category = it,
                modifier = Modifier.padding(4.dp).clickable{ onTransactionClick(it) }
            )
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
        "Tecnología","Deportes", "Música","Cine", "Literatura"
    )
    ListCategories(categoriasEjemplo, onTransactionClick = {})
}