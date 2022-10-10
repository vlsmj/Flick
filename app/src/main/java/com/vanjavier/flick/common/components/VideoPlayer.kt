package com.vanjavier.flick.common.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.Lifecycle
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView
import com.vanjavier.flick.R
import com.vanjavier.flick.domain.model.Movie

/**
 * The video player that will play the movie's trailer using ExoPlayer.
 *
 * @param movie the movie model that will provide the details.
 * @param onClose click listener for exiting the movie details screen.
 */
@Composable
fun VideoPlayer(
    movie: Movie,
    onClose: () -> Unit,
) {
    val movieDetail = remember {
        mutableStateOf(movie)
    }

    movieDetail.value.run {
        val context = LocalContext.current

        // Initialize ExoPlayer
        val exoPlayer = remember {
            ExoPlayer.Builder(context).build().apply {
                val mediaItem = MediaItem.Builder()
                    .setUri(trailerUrl)
                    .build()
                setMediaItem(mediaItem)

                playWhenReady = true
                prepare()
            }
        }

        // Listen to android lifecycle for ExoPlayer's controls
        ComposableLifecycle { _, event ->
            when (event) {
                Lifecycle.Event.ON_PAUSE -> {
                    exoPlayer.pause()
                }
                Lifecycle.Event.ON_RESUME -> {
                    exoPlayer.play()
                }
                else -> {}
            }
        }

        val constraints = ConstraintSet {
            val exoPlayer = createRefFor("exoPlayer")
            val exitButton = createRefFor("exitButton")

            constrain(exoPlayer) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
                end.linkTo(parent.end)

                width = Dimension.ratio("16:9")
                height = Dimension.fillToConstraints
            }

            constrain(exitButton) {
                top.linkTo(exoPlayer.top)
                end.linkTo(exoPlayer.end)
            }
        }

        Column(modifier = Modifier.fillMaxWidth()) {
            ConstraintLayout(constraints, modifier = Modifier
                .fillMaxWidth()) {
                DisposableEffect(
                    AndroidView(
                        modifier = Modifier.layoutId("exoPlayer"),
                        factory = {
                            // ExoPlayer style view
                            StyledPlayerView(context).apply {
                                player = exoPlayer
                                resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                            }
                        }
                    )
                ) {
                    onDispose {
                        // Dispose ExoPlayer when destroyed
                        exoPlayer.stop()
                        exoPlayer.release()
                    }
                }

                Image(painterResource(id = R.drawable.ic_close),
                    contentDescription = "close",
                    modifier = Modifier
                        .size(56.dp)
                        .padding(16.dp)
                        .layoutId("exitButton")
                        .clickable {
                            onClose()
                        })
            }
        }
    }
}