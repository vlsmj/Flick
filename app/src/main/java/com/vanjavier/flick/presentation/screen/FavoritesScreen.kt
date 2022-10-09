package com.vanjavier.flick.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vanjavier.flick.common.components.EmptyListPlaceholder
import com.vanjavier.flick.common.components.MovieItem
import com.vanjavier.flick.common.components.TopBar
import com.vanjavier.flick.presentation.viewmodel.FavoriteMovieViewModel

@Composable
fun FavoritesScreen(
    navController: NavController,
    viewModel: FavoriteMovieViewModel = hiltViewModel(),
) {
    val state = viewModel.movieState.value

    Column(modifier = Modifier.fillMaxSize()) {
        TopBar(title = "Favorites")

        if (state.data.isNotEmpty()) {
            LazyVerticalGrid(columns = GridCells.Fixed(3),
                modifier = Modifier
                    .padding(start = 4.dp, end = 4.dp, bottom = 64.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(1.dp),
                horizontalArrangement = Arrangement.spacedBy(1.dp)) {

                items(state.data) { movie ->
                    MovieItem(movie = movie,
                        onItemClick = {

                        },
                        onStarClick = {
                            if (it.isFavorite) {
                                viewModel.unFavoriteMovie(it.title)
                            }
                        })
                }
            }
        } else {
            EmptyListPlaceholder()
        }
    }
}