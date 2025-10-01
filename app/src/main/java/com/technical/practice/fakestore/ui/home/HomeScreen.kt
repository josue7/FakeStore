package com.technical.practice.fakestore.ui.home

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.ui.FakeStoreAppBar
import com.technical.practice.fakestore.ui.navigation.NavigationDestination
import com.technical.practice.fakestore.ui.tab.tabs
import com.technical.practice.fakestore.ui.theme.FakeStoreTheme

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@Composable
fun HomeScreen () {
    var title by remember { mutableIntStateOf(HomeDestination.titleRes) }
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
                titleChange = { title = it },
                modifier = Modifier.padding(innerPadding)
            )

        }
    }
}

@Composable
fun HomeBody (
    titleChange: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val pageState = rememberPagerState( pageCount = { tabs.size } )
    var selectedTabIndex by remember { mutableIntStateOf(0) }
    val resTitle = when (selectedTabIndex) {
        0 -> DebtDestination.titleRes
        1 -> PersonViewDestination.titleRes
        else -> DebtDestination.titleRes
    }

    Column {
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