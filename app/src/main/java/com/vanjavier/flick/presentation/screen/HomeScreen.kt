package com.vanjavier.flick.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vanjavier.flick.R
import com.vanjavier.flick.common.Constants.FEATURED_GENRE
import com.vanjavier.flick.common.Genre.*
import com.vanjavier.flick.common.components.IconText
import com.vanjavier.flick.common.components.ListRowMovies
import com.vanjavier.flick.presentation.viewmodel.MovieViewModel
import com.vanjavier.flick.ui.theme.Black

@Composable
fun HomeScreen(
    navController: NavController,
    randomFeaturedMovieIndex: Int,
    viewModel: MovieViewModel = hiltViewModel(),
) {
    val state = viewModel.movieState.value

    val scrollState = rememberScrollState()

    val genres = listOf(
        COMEDY,
        ACTION_ADVENTURE,
        SCI_FI_FANTASY,
        DRAMA,
        ROMANCE
    )

    if (state.data.isNotEmpty()) {
        val movies = state.data
        val configuration = LocalConfiguration.current
        val screenHeight = configuration.screenHeightDp.dp

        val featuredMovie = if (randomFeaturedMovieIndex <= movies.count() - 1) {
            movies[randomFeaturedMovieIndex]
        } else {
            movies[0]
        }

        val constraints = ConstraintSet {
            val featuredThumbnail = createRefFor("featuredThumbnail")
            val featuredButtons = createRefFor("featuredButtons")

            constrain(featuredThumbnail) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)
            }

            constrain(featuredButtons) {
                start.linkTo(featuredThumbnail.start)
                end.linkTo(featuredThumbnail.end)
                bottom.linkTo(featuredThumbnail.bottom)
            }
        }

        Column(modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(bottom = 70.dp)) {
            Box {
                ConstraintLayout(constraints, modifier = Modifier.fillMaxWidth()) {
                    AsyncImage(model = featuredMovie.thumbnailUrlFeatured,
                        contentDescription = "image",
                        contentScale = ContentScale.Crop,
                        error = painterResource(id = R.drawable.ic_popcorn_placeholder),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(screenHeight / 2)
                            .layoutId("featuredThumbnail"))

                    Row(verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        modifier = Modifier
                            .fillMaxWidth()
                            .layoutId("featuredButtons")
                            .background(brush = Brush.verticalGradient(colors = listOf(Color.Transparent, Black)))
                            .padding(16.dp)) {
                        IconText(icon = painterResource(id = R.drawable.ic_baseline_star_unfavorite_24), text = "Favorite")
                        IconText(icon = painterResource(id = R.drawable.ic_baseline_info_24), text = "Info")
                    }
                }
            }

            genres.forEach { genre ->
                val moviesByGenre = movies.filter {
                    it.genre == genre.name
                }

                if (moviesByGenre.isNotEmpty()) {
                    ListRowMovies(if (genre.name == FEATURED_GENRE) {
                        stringResource(id = R.string.title_featured)
                    } else {
                        genre.name
                    }, moviesByGenre, onItemClick = {

                    }, onStarClick = {
                        if (it.isFavorite) {
                            viewModel.unFavoriteMovie(it.title)
                        } else {
                            viewModel.favoriteMovie(it.title)
                        }
                    })
                }
            }
        }
    }
}