package com.vanjavier.flick.data.remote.dto

import com.vanjavier.flick.common.Constants.THUMBNAIL_SIZE
import com.vanjavier.flick.common.Constants.THUMBNAIL_SIZE_FEATURED
import com.vanjavier.flick.common.Genre
import com.vanjavier.flick.domain.model.Movie

data class MovieDto(
    val artistName: String?,
    val artworkUrl100: String?,
    val artworkUrl30: String?,
    val artworkUrl60: String?,
    val collectionArtistId: Int?,
    val collectionArtistViewUrl: String?,
    val collectionCensoredName: String?,
    val collectionExplicitness: String?,
    val collectionHdPrice: Double?,
    val collectionId: Int?,
    val collectionName: String?,
    val collectionPrice: Double?,
    val collectionViewUrl: String?,
    val contentAdvisoryRating: String?,
    val country: String?,
    val currency: String?,
    val discCount: Int?,
    val discNumber: Int?,
    val hasITunesExtras: Boolean,
    val kind: String?,
    val longDescription: String?,
    val previewUrl: String?,
    val primaryGenreName: String?,
    val releaseDate: String?,
    val trackCensoredName: String?,
    val trackCount: Int?,
    val trackExplicitness: String?,
    val trackHdPrice: Double?,
    val trackId: Int?,
    val trackName: String?,
    val trackNumber: Int?,
    val trackPrice: Double?,
    val trackTimeMillis: Int?,
    val trackViewUrl: String?,
    val wrapperType: String?,
)

fun MovieDto.toMovie(): Movie {
    val title = trackName ?: ""
    val thumbnailUrlFeatured = artworkUrl100?.replace("100x100bb", THUMBNAIL_SIZE_FEATURED) ?: ""
    val thumbnailUrl = artworkUrl100?.replace("100x100bb", THUMBNAIL_SIZE) ?: ""
    val genre = primaryGenreName ?: ""
    val isFeatured = genre == Genre.SCI_FI_FANTASY.name

    return Movie(
        title = title,
        thumbnailUrlFeatured = thumbnailUrlFeatured,
        thumbnailUrl = thumbnailUrl,
        genre = genre,
        isFeatured = isFeatured
    )
}