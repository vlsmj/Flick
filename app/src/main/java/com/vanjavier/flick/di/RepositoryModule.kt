package com.vanjavier.flick.di

import com.vanjavier.flick.data.repositoryimpl.MovieRepositoryImpl
import com.vanjavier.flick.domain.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Provides all repositories here using bind for less code, skips implementation.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMovieRepositoryImpl(
        movieRepositoryImpl: MovieRepositoryImpl,
    ): MovieRepository
}