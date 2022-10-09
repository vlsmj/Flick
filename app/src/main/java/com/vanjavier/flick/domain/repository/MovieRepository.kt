package com.vanjavier.flick.domain.repository

import com.vanjavier.flick.common.util.Resource
import com.vanjavier.flick.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getAllMovies(): Flow<Resource<List<Movie>>>

    suspend fun getAllFavoriteMovies(): Flow<Resource<List<Movie>>>

    suspend fun favoriteMovie(title: String)

    suspend fun unFavoriteMovie(title: String)
}