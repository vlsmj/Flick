package com.vanjavier.flick.common.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.vanjavier.flick.common.Constants.LIST_LIMIT_SIZE
import com.vanjavier.flick.domain.model.Movie

@Composable
fun ListRowMovies(
    category: String,
    movies: List<Movie>,
    onItemClick: (movie: Movie) -> Unit,
    onStarClick: (movie: Movie) -> Unit,
) {
    Text(text = category,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 8.dp, top = 10.dp, end = 8.dp))
    LazyRow(contentPadding = PaddingValues(horizontal = 4.dp),
        modifier = Modifier
            .fillMaxWidth()) {

        items(movies.take(LIST_LIMIT_SIZE)) {
            MovieItem(movie = it,
                onItemClick = { movie ->
                    onItemClick(movie)
                },
                onStarClick = { movie ->
                    onStarClick(movie)
                }, isFeatured = it.isFeatured)
        }
    }
}