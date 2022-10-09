package com.vanjavier.flick.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.vanjavier.flick.common.Constants.KEY_MOVIE
import com.vanjavier.flick.common.components.MovieDetails
import com.vanjavier.flick.common.components.VideoPlayer
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.ui.theme.FlickTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {

    private lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent?.let { intent ->
            movie = intent.getSerializableExtra(KEY_MOVIE) as Movie
        }

        setContent {
            FlickTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    if (::movie.isInitialized) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            VideoPlayer(movie) {
                                finish()
                            }

                            MovieDetails(movie)
                        }
                    }
                }
            }
        }
    }
}