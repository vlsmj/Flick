package com.vanjavier.flick.di

import android.app.Application
import androidx.room.Room
import com.vanjavier.flick.common.Constants.BASE_URL
import com.vanjavier.flick.data.datasource.MovieDao
import com.vanjavier.flick.data.datasource.MovieDatabase
import com.vanjavier.flick.data.remote.ITunesApi
import com.vanjavier.flick.domain.repository.MovieRepository
import com.vanjavier.flick.domain.usecase.GetAllMoviesUseCase
import com.vanjavier.flick.domain.usecase.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * Provides the database for this application.
     */
    @Provides
    @Singleton
    fun provideMovieDatabase(app: Application): MovieDatabase {
        return Room.databaseBuilder(
            app,
            MovieDatabase::class.java, "db_movie")
            .fallbackToDestructiveMigration()
            .build()
    }

    /**
     * Provides the API for this application.
     */
    @Provides
    @Singleton
    fun provideITunesApi(): ITunesApi {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ITunesApi::class.java)
    }

    /**
     * Provides MovieDao for repositories.
     */
    @Provides
    @Singleton
    fun provideMovieDao(db: MovieDatabase): MovieDao {
        return db.movieDao()
    }

    /**
     * Provides all the uses cases to be used in ViewModels.
     */
    @Provides
    @Singleton
    fun provideUseCases(movieRepository: MovieRepository) = UseCases(
        getAllMoviesUseCase = GetAllMoviesUseCase(movieRepository)
    )
}