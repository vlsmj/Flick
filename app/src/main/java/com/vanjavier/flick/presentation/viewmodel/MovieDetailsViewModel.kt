package com.vanjavier.flick.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanjavier.flick.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    fun favoriteMovie(title: String) {
        viewModelScope.launch {
            useCases.favoriteMovieUseCase(title)
        }
    }

    fun unFavoriteMovie(title: String) {
        viewModelScope.launch {
            useCases.unFavoriteMovieUseCase(title)
        }
    }
}