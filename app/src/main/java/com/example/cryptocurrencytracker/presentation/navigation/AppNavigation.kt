package com.example.cryptocurrencytracker.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cryptocurrencytracker.presentation.cryptocurrencydetails.CryptocurrencyDetailsScreen
import com.example.cryptocurrencytracker.presentation.cryptocurrencyhome.CryptocurrencyHomeScreen

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.Home.route, modifier = modifier) {
        composable(Screen.Home.route) {
            CryptocurrencyHomeScreen(
                onNavigateToDetails = { symbol ->
                    navController.navigate(Screen.Details.createRoute(symbol))
                }
            )
        }
        composable(
            route = Screen.Details.route,
            arguments = listOf(navArgument("symbol") {
                type = NavType.StringType
            })
        ) { backStackEntry ->
            CryptocurrencyDetailsScreen(
                symbol = backStackEntry.arguments?.getString("symbol") ?: "",
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
