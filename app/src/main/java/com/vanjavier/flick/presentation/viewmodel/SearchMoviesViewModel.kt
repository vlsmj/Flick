package com.vanjavier.flick.presentation.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vanjavier.flick.common.Constants.DELAY_SEARCH_TIME
import com.vanjavier.flick.common.util.Resource
import com.vanjavier.flick.domain.usecase.UseCases
import com.vanjavier.flick.presentation.state.MovieState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val useCases: UseCases,
) : ViewModel() {

    private var job: Job? = null

    var movieState = mutableStateOf(MovieState())
        private set

    init {
        getAllFavoriteMovies()
    }

    fun searchMovies(query: String) {
        job?.let {
            if (it.isActive) it.cancel()
        }

        job = viewModelScope.launch {
            delay(DELAY_SEARCH_TIME)
            useCases.searchMoviesUseCase(query).collect {
                when (it) {
                    is Resource.Loading -> {
                        movieState.value = movieState.value.copy(data = it.data ?: mutableListOf(), isLoading = true)
                    }
                    is Resource.Success -> {
                        movieState.value = movieState.value.copy(data = it.data ?: mutableListOf(), success = true, isLoading = false)
                    }
                    is Resource.Error -> {
                        movieState.value = movieState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                    }
                }
            }
        }
    }

    fun getFavoriteStatus(id: Int): Boolean {
        return movieState.value.favoriteMovies.find { movie ->
            movie.id == id
        }?.isFavorite ?: false
    }

    private fun getAllFavoriteMovies() {
        useCases.getAllFavoriteMoviesUseCase().onEach {
            when (it) {
                is Resource.Loading -> {
                    movieState.value = movieState.value.copy(favoriteMovies = it.data ?: mutableListOf(), isLoading = true)
                }
                is Resource.Success -> {
                    movieState.value = movieState.value.copy(favoriteMovies = it.data ?: mutableListOf(), isLoading = false)
                }
                is Resource.Error -> {
                    movieState.value = movieState.value.copy(errorMessage = it.errorMessage, isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}