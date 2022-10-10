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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: Movie)

    @Query("UPDATE movie SET isFavorite = 1 WHERE id = :id")
    suspend fun favoriteMovie(id: Int)

    @Query("UPDATE movie SET isFavorite = 0 WHERE id = :id")
    suspend fun unFavoriteMovie(id: Int)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()
}