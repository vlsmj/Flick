package com.vanjavier.flick.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.vanjavier.flick.R
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.presentation.viewmodel.MovieDetailsViewModel

/**
 * Composable for showing the details of the movie.
 *
 * @param movie the movie model that will provide the details.
 * @param viewModel the ViewModel for this composable.
 */
@Composable
fun MovieDetails(
    movie: Movie,
    viewModel: MovieDetailsViewModel = hiltViewModel(),
) {
    val movieDetails = remember {
        mutableStateOf(movie)
    }

    val scrollState = rememberScrollState()

    movieDetails.value.run {
        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(8.dp)) {
            Image(painterResource(id = R.drawable.ic_logo), contentDescription = "logo",
                modifier = Modifier.size(width = 44.dp, height = 20.dp))
            Row(verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(title,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(0.8f))

                if (isFavorite) {
                    IconText(icon = painterResource(id = R.drawable.ic_baseline_star_favorite_24), text = "Favorite",
                        modifier = Modifier.weight(0.2f)) {
                        movieDetails.value = movieDetails.value.copy(isFavorite = false)
                        viewModel.insertMovie(this@run.apply {
                            isFavorite = false
                        })
                    }
                } else {
                    IconText(icon = painterResource(id = R.drawable.ic_baseline_star_unfavorite_24), text = "Favorite",
                        modifier = Modifier.weight(0.2f)) {
                        movieDetails.value = movieDetails.value.copy(isFavorite = true)
                        viewModel.insertMovie(this@run.apply {
                            isFavorite = true
                        })
                    }
                }
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = releaseYear, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                BorderedBoxText(advisoryRating)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = genre, fontSize = 12.sp)
                Spacer(modifier = Modifier.width(4.dp))
                BorderedBoxText(country)
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = runtime, fontSize = 12.sp)
                Text(text = " - $price", fontSize = 12.sp)
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = longDescription,
                fontSize = 12.sp,
                textAlign = TextAlign.Justify)
        }
    }
}