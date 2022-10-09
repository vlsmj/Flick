package com.vanjavier.flick.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.vanjavier.flick.R
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.ui.theme.Gray

@Composable
fun MovieThumbnail(
    movie: Movie,
    onItemClick: (movie: Movie) -> Unit = {},
) {
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
}