package com.vanjavier.flick.data.remote

import com.vanjavier.flick.data.remote.dto.MovieDto
import com.vanjavier.flick.data.remote.dto.WrapperListDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ITunesApi {

    @GET("search?term=star&media=movie&;all")
    suspend fun getDefaultMovies(): WrapperListDto<MovieDto>

    @GET("search?media=movie&;all&limit=10")
    suspend fun searchMovies(@Query("term") query: String): WrapperListDto<MovieDto>
}