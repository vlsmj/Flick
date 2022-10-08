package com.vanjavier.flick.domain.usecase

import com.vanjavier.flick.common.util.Resource
import com.vanjavier.flick.domain.model.Movie
import com.vanjavier.flick.domain.repository.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Provides specific function for this use case with the help of the repository.
 *
 * @param repository invoke specific function in the repository for this use case.
 */
class GetAllMoviesUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    operator fun invoke(): Flow<Resource<List<Movie>>> = flow {
        repository.getAllMovies().collect {
            emit(it)
        }
    }
}