package com.vanjavier.flick.domain.usecase

import com.vanjavier.flick.domain.repository.MovieRepository
import javax.inject.Inject

/**
 * Provides specific function for this use case with the help of the repository.
 *
 * @param repository invoke specific function in the repository for this use case.
 */
class UnFavoriteMovieUseCase @Inject constructor(
    private val repository: MovieRepository,
) {
    suspend operator fun invoke(id: Int) {
        repository.unFavoriteMovie(id)
    }
}