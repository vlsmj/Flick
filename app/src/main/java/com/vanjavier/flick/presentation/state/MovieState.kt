package com.vanjavier.flick.presentation.state

import com.vanjavier.flick.common.util.UiText
import com.vanjavier.flick.domain.model.Movie

data class MovieState(
    val isLoading: Boolean = false,
    val data: List<Movie> = mutableListOf(),
    val favoriteMovies: List<Movie> = mutableListOf(),
    val query: String = "",
    val success: Boolean = false,
    val errorMessage: UiText? = UiText.DynamicString(""),
)
