package com.vanjavier.flick.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.domain.usecase.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    fun insertMovie(movie: Movie) {
        viewModelScope.launch {
            useCases.insertMovieUseCase(movie)
        }
    }
}