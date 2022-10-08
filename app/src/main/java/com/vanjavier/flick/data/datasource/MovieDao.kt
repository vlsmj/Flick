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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<Movie>)

    @Query("DELETE FROM movie")
    suspend fun deleteAllMovies()
}