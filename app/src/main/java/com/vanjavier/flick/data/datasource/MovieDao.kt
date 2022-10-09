package com.vanjavier.flick.data.datasource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vanjavier.flick.domain.model.Movie

@Dao
interface MovieDao {

    @Query("SELECT * FROM movie")
    suspend fun getAllMovies(): List<Movie>

    @Query("SELECT * FROM movie WHERE isFavorite = 1")
    suspend fun getAllFavoriteMovies(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("UPDATE movie SET isFavorite = 1 WHERE title = :title")
    suspend fun favoriteMovie(title: String)

    @Query("UPDATE movie SET isFavorite = 0 WHERE title = :title")
    suspend fun unFavoriteMovie(title: String)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()
}