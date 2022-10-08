package com.vanjavier.flick.presentation

sealed class Screen(val route: String) {
    object FavoritesScreen : Screen("favorites_screen")
    object HomeScreen : Screen("home_screen")
    object SearchScreen : Screen("search_screen")
}
