package com.vanjavier.flick.data.repositoryimpl

import com.vanjavier.flick.R
import com.vanjavier.flick.common.util.Resource
import com.vanjavier.flick.common.util.UiText
import com.vanjavier.flick.data.datasource.MovieDao
import com.vanjavier.flick.data.remote.ITunesApi
import com.vanjavier.flick.data.remote.dto.toMovie
import com.vanjavier.flick.domain.repository.MovieRepository
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

/**
 * Injects necessary dependencies by Hilt
 *
 * @param api interface for the remote server.
 * @param movieDao dao from database for movies.
 */
class MovieRepositoryImpl @Inject constructor(
    private val api: ITunesApi,
    private val movieDao: MovieDao,
) : MovieRepository {

    override suspend fun getAllMovies() = flow {
        try {
            val savedMovies = movieDao.getAllMovies()

            emit(Resource.Loading(savedMovies))

            val defaultMovies = api.getDefaultMovies().results.map {
                it.toMovie()
            }

            // Delete saved movies and insert new movies fetched from the API.
            movieDao.deleteAllMovies()
            movieDao.insertMovies(defaultMovies)

            emit(Resource.Success(movieDao.getAllMovies()))
        } catch (e: HttpException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_exception_message)))
        } catch (e: IOException) {
            emit(Resource.Error(UiText.StringResource(R.string.error_io_exception_message)))
        }
    }
}