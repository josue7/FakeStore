package com.technical.practice.fakestore.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.technical.practice.fakestore.R
import com.technical.practice.fakestore.ui.navigation.FakeStoreNavGraph


@Composable
fun FakeStoreApp ( navController: NavHostController = rememberNavController() ) {
    FakeStoreNavGraph( navController = navController )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FakeStoreAppBar (
    title: String,
    canNavigationBack: Boolean,
    modifier: Modifier,
    navigateUp: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(title) },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer
        ),
        modifier = modifier,
        navigationIcon = {
            if (canNavigationBack) {
                IconButton ( onClick = navigateUp ) {
                    Icon (
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.button_text_back)
                    )
                }
            }
        }
    )

}