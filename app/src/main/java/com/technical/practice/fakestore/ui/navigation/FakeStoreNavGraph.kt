package com.technical.practice.fakestore.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.technical.practice.fakestore.ui.detail.DetailDestination
import com.technical.practice.fakestore.ui.detail.ProductDetailScreen
import com.technical.practice.fakestore.ui.home.HomeDestination
import com.technical.practice.fakestore.ui.home.HomeScreen
import com.technical.practice.fakestore.ui.product.ProductDestination
import com.technical.practice.fakestore.ui.product.ProductScreen

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
            HomeScreen(
                navigateToView = { category ->
                    navController.navigate("${ProductDestination.route}/${category}")
                }
            )
        }

        composable (
            route = ProductDestination.routeWithArgs,
            arguments = listOf(
                navArgument (ProductDestination.PRODUCRCATEGORY) {
                    type = NavType.StringType
                }
            )
        ) {
            ProductScreen (
                navigateToView = { productId ->
                    navController.navigate("${DetailDestination.route}/${productId}")
                },
                onBackScreen = {
                    navController.popBackStack()
                }
            )
        }

        composable (
            route = DetailDestination.routeWithArgs,
            arguments = listOf (
                navArgument (DetailDestination.PRODUCT_ID) {
                    type = NavType.IntType
                }
            )
        ) {
            ProductDetailScreen(onBackScreen = { navController.popBackStack() })
        }
    }
}