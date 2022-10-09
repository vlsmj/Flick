package com.vanjavier.flick.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Movie(
    @PrimaryKey
    val id: Int,
    val title: String,
    val thumbnailUrlFeatured: String,
    val thumbnailUrl: String,
    val genre: String,
    val trailerUrl: String,
    val advisoryRating: String,
    val country: String,
    val longDescription: String,
    val runtime: String,
    val artistName: String,
    val releaseDate: String,
    val releaseYear: String,
    var isFavorite: Boolean = false,
    var isFeatured: Boolean = false,
) : Serializable
