package com.vanjavier.flick.common.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.vanjavier.flick.R
import com.vanjavier.flick.domain.model.Movie

/**
 * The single composable movie.
 *
 * @param movie the movie model that will provide the details.
 * @param onItemClick click listener for this movie.
 * @param onStarClick click listener for adding or removing this as a favorite movie.
 */
@Composable
fun MovieItem(
    movie: Movie,
    isFeatured: Boolean = false,
    onItemClick: (movie: Movie) -> Unit,
    onStarClick: (movie: Movie) -> Unit,
) {
    val width = 120.dp
    val height = 164.dp

    Box(modifier = Modifier
        .height(if (isFeatured) {
            height * 1.6f
        } else {
            height
        })
        .width(if (isFeatured) {
            width * 1.4f
        } else {
            width
        })
        .padding(4.dp)
        .clip(RoundedCornerShape(4.dp))) {
        MovieThumbnail(movie) {
            onItemClick(it)
        }
        Box(modifier = Modifier
            .align(Alignment.BottomEnd)
            .padding(horizontal = 8.dp, vertical = 4.dp)) {

            if (movie.isFavorite) {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_star_favorite_24),
                    contentDescription = "favorited",
                    modifier = Modifier
                        .size(18.dp)
                        .clickable {
                            onStarClick(movie)
                        })
            } else {
                Icon(painter = painterResource(id = R.drawable.ic_baseline_star_unfavorite_24),
                    contentDescription = "unfavorited",
                    modifier = Modifier
                        .size(18.dp)
                        .clickable {
                            onStarClick(movie)
                        })
            }
        }
    }
}