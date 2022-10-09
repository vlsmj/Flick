package com.vanjavier.flick.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.*
import com.vanjavier.flick.R
import com.vanjavier.flick.common.Constants.SPLASH_LOADING_TIME
import com.vanjavier.flick.common.extensions.openActivity
import com.vanjavier.flick.ui.theme.FlickTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class LoadingActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlickTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Box(modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center) {
                        val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.ic_popcorn_raw))
                        val lottieProgress by animateLottieCompositionAsState(composition = composition, iterations = LottieConstants.IterateForever)
                        LottieAnimation(
                            composition = composition,
                            progress = {
                                lottieProgress
                            },
                            Modifier.size(216.dp)
                        )
                    }
                    LaunchedEffect(Unit) {
                        delay(SPLASH_LOADING_TIME)
                        openActivity(MainActivity::class.java, this@LoadingActivity)
                    }
                }
            }
        }
    }
}