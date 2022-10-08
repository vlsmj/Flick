package com.vanjavier.flick.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.vanjavier.flick.R
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.ui.theme.Gray

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
        AsyncImage(model = movie.thumbnailUrl,
            contentDescription = movie.title,
            contentScale = ContentScale.Crop,
            placeholder = painterResource(id = R.drawable.ic_popcorn_placeholder),
            error = painterResource(id = R.drawable.ic_popcorn_placeholder),
            modifier = Modifier
                .fillMaxSize()
                .background(Gray)
                .clickable {
                    onItemClick(movie)
                })

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