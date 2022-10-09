package com.vanjavier.flick.data.datasource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vanjavier.flick.domain.model.Movie

@Database(entities = [Movie::class], version = 2, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
}