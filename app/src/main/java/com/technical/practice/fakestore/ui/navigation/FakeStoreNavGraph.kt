package com.technical.practice.fakestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.technical.practice.fakestore.ui.home.HomeDestination
import com.technical.practice.fakestore.ui.home.HomeScreen

@Composable
fun FakeStoreNavGraph (
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        composable ( route = HomeDestination.route ) {
            HomeScreen()
        }
    }
}