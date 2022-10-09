package com.vanjavier.flick.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.vanjavier.flick.common.Constants
import com.vanjavier.flick.common.extensions.openActivity
import com.vanjavier.flick.presentation.screen.SearchScreen
import com.vanjavier.flick.ui.theme.FlickTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FlickTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    SearchScreen(onNavigateToDetails = { movie ->
                        openActivity(MovieDetailsActivity::class.java, this@SearchActivity) {
                            putSerializable(Constants.KEY_MOVIE, movie)
                        }
                    }) {
                        finish()
                    }
                }
            }
        }
    }
}