package com.vanjavier.flick.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vanjavier.flick.domain.model.Movie

/**
 * The single composable movie shown from search.
 *
 * @param movie the movie model that will provide the details.
 * @param onItemClick click listener for this movie.
 */
@Composable
fun SearchMovieItem(
    movie: Movie,
    onItemClick: (movie: Movie) -> Unit,
) {
    val width = 120.dp
    val height = 164.dp

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onItemClick(movie)
            }) {

        Box(modifier = Modifier
            .height(height)
            .width(width)
            .clip(RoundedCornerShape(4.dp))) {
            MovieThumbnail(movie) {
                onItemClick(movie)
            }
        }

        Text(text = movie.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 14.sp,
            modifier = Modifier.padding(8.dp))
    }
}