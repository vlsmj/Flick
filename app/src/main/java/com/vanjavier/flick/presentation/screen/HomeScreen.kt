package com.vanjavier.flick.presentation.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vanjavier.flick.presentation.viewmodel.MovieViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val state = viewModel.movieState.value

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(state.data) { movie ->
            Text(text = movie.title)
        }
    }
}