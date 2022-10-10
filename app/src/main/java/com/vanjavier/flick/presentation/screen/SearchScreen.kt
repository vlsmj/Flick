package com.vanjavier.flick.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanjavier.flick.R
import com.vanjavier.flick.common.components.EmptyListPlaceholder
import com.vanjavier.flick.common.components.InputTextField
import com.vanjavier.flick.common.components.SearchMovieItem
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.presentation.viewmodel.SearchMoviesViewModel
import kotlinx.coroutines.delay

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel(),
    onNavigateToDetails: (movie: Movie) -> Unit,
    onBack: () -> Unit,
) {
    val state = viewModel.movieState.value

    val focusRequester = remember { FocusRequester() }

    val keyboard = LocalSoftwareKeyboardController.current

    var closeKeyboardState by remember {
        mutableStateOf(false)
    }

    // Show keyboard on first enter.
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        delay(100)
        keyboard?.show()
    }

    if (closeKeyboardState) {
        keyboard?.hide()
    }

    if (state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(24.dp)
            )
        }
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Row(verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(start = 16.dp, end = 8.dp)) {

            Icon(painterResource(id = R.drawable.ic_baseline_arrow_back_ios_24), contentDescription = "back",
                modifier = Modifier
                    .clickable {
                        onBack()
                    }
                    .size(24.dp))

            InputTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(start = 8.dp)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(32.dp))
                    .clip(RoundedCornerShape(32.dp))
                    .background(Color.DarkGray)
                    .focusRequester(focusRequester),
                hint = "Search movie",
                query = state.query
            ) { input, closeKeyboard ->
                viewModel.movieState.value = viewModel.movieState.value.copy(query = input)

                if (input.isNotBlank()) {
                    viewModel.searchMovies(input)
                } else {
                    viewModel.movieState.value = viewModel.movieState.value.copy(data = mutableListOf())
                }

                closeKeyboardState = closeKeyboard
            }
        }

        if (state.data.isNotEmpty()) {
            LazyColumn(modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)) {
                items(state.data) { movie ->
                    SearchMovieItem(movie = movie, onItemClick = {
                        val selectedMovie = it
                        selectedMovie.isFavorite = viewModel.getFavoriteStatus(selectedMovie.id)

                        onNavigateToDetails(selectedMovie)
                    })
                }
            }
        } else if (state.success && !state.isLoading) {
            EmptyListPlaceholder()
        }
    }
}