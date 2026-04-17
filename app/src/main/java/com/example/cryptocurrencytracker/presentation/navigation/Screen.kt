package com.example.cryptocurrencytracker.presentation.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Details : Screen("details/{symbol}") {
        fun createRoute(symbol: String) = "details/$symbol"
    }
}