package com.vanjavier.flick.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanjavier.flick.common.util.Resource
import com.vanjavier.flick.domain.usecase.UseCases
import com.vanjavier.flick.presentation.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    var movieState = mutableStateOf(MovieState())
        private set

    init {
        getAllMovies()
    }

    private fun getAllMovies() {
        useCases.getAllMoviesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    movieState.value = movieState.value.copy(data = it.data ?: mutableListOf(), isLoading = true)
                }
                is Resource.Success -> {
                    movieState.value = movieState.value.copy(data = it.data ?: mutableListOf(), isLoading = false)
                }
                is Resource.Error -> {
                    movieState.value = movieState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}