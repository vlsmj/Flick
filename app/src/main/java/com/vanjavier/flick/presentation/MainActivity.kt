package com.vanjavier.flick.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vanjavier.flick.R
import com.vanjavier.flick.common.BottomNavItem
import com.vanjavier.flick.common.components.BottomNavigationBar
import com.vanjavier.flick.common.components.TopBar
import com.vanjavier.flick.presentation.screen.FavoritesScreen
import com.vanjavier.flick.presentation.screen.HomeScreen
import com.vanjavier.flick.ui.theme.FlickTheme
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val randomFeaturedMovieIndex = Random().nextInt(10)

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlickTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    Scaffold(
                        bottomBar = {
                            BottomNavigationBar(
                                items = listOf(
                                    BottomNavItem(
                                        name = "Home",
                                        route = Screen.HomeScreen.route,
                                        icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_home_24)
                                    ),
                                    BottomNavItem(
                                        name = "Favorites",
                                        route = Screen.FavoritesScreen.route,
                                        icon = ImageVector.vectorResource(id = R.drawable.ic_baseline_star_favorite_24)
                                    ),
                                ),
                                navController = navController,
                                onItemClick = {
                                    navController.navigate(it.route)
                                }
                            )
                        }
                    ) {
                        NavHost(navController = navController, startDestination = Screen.HomeScreen.route) {
                            composable(Screen.HomeScreen.route) {
                                HomeScreen(navController, randomFeaturedMovieIndex)
                                TopBar(logo = painterResource(id = R.drawable.ic_logo))
                            }
                            composable(Screen.FavoritesScreen.route) {
                                FavoritesScreen(navController)
                            }
                        }
                    }
                }
            }
        }
    }
}