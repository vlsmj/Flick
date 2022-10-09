package com.vanjavier.flick.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie(
    @PrimaryKey
    val title: String,
    val thumbnailUrlFeatured: String,
    val thumbnailUrl: String,
    val genre: String,
    var isFavorite: Boolean = false,
    var isFeatured: Boolean = false,
)
