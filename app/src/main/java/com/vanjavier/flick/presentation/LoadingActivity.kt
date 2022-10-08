package com.vanjavier.flick.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.LaunchedEffect
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
                Text(text = "Loading Screen...")

                LaunchedEffect(Unit) {
                    delay(3000)
                    openActivity(MainActivity::class.java, this@LoadingActivity)
                }
            }
        }
    }
}