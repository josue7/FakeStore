package com.technical.practice.fakestore.ui.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.data.database.product.Product
//import com.technical.practice.fakestore.data.database.category.Category
import com.technical.practice.fakestore.ui.AppViewModelProvider
import com.technical.practice.fakestore.ui.FakeStoreAppBar
import com.technical.practice.fakestore.ui.category.CategoryDestination
import com.technical.practice.fakestore.ui.category.CategoryScreen
import com.technical.practice.fakestore.ui.navigation.NavigationDestination
import com.technical.practice.fakestore.ui.product.ProductFavoriteDestination
import com.technical.practice.fakestore.ui.product.ProductFavoriteScreen
import com.technical.practice.fakestore.ui.tab.tabs
import com.technical.practice.fakestore.ui.theme.FakeStoreTheme

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen (
    viewModel: HomeViewModel = viewModel(factory = AppViewModelProvider.Factory),
    navigateToView: (String) -> Unit
) {

    var title by remember { mutableIntStateOf(HomeDestination.titleRes) }
    Log.i("Variable sta", viewModel.uiState.toString())
    if (viewModel.uiState !is HomeUiState.Success) {
        Log.i("Variable", viewModel.categories.collectAsState().value.toString())
    }

    FakeStoreTheme {
        Scaffold (
            topBar = {
                FakeStoreAppBar(
                    title = stringResource(title),
                    modifier = Modifier,
                    canNavigationBack = false
                )
            }
        ) { innerPadding ->
            HomeBody(
                listCategories = viewModel.categories.collectAsState().value,
                titleChange = { title = it },
                mavigateToView = navigateToView,
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}

@Composable
fun HomeBody (
    listCategories: List<String> = emptyList(),
    productFavorite: List<Product> = emptyList(),
    titleChange: (Int) -> Unit,
    mavigateToView: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val pageState = rememberPagerState( pageCount = { tabs.size } )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val resTitle = when (selectedTabIndex) {
        0 -> CategoryDestination.titleRes
        1 -> ProductFavoriteDestination.titleRes
        else -> CategoryDestination.titleRes
    }

    titleChange (resTitle)

    Column (modifier = modifier) {
        Column (
            modifier = Modifier.weight(1f)
        ) {
            when (selectedTabIndex) {
                0 -> CategoryScreen(listCategories, navigateToView = mavigateToView)
                1 -> ProductFavoriteScreen(listProducts = productFavorite)
            }
        }
        TabRow(
            selectedTabIndex = selectedTabIndex,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            tabs.forEachIndexed { index, currentTab ->
                Tab(
                    selected = (selectedTabIndex == index),
                    text = { Text(stringResource(currentTab.title)) },
                    icon = {
                        Icon(
                            if (pageState.currentPage == index)
                                currentTab.iconSelected
                            else
                                currentTab.iconUnselected,
                            contentDescription = ""
                        )
                    },
                    onClick = {
                        selectedTabIndex = index
                    }
                )
            }
        }
    }
}