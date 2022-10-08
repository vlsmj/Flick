package com.vanjavier.flick.data.remote

import com.vanjavier.flick.data.remote.dto.MovieDto
import com.vanjavier.flick.data.remote.dto.WrapperListDto
import retrofit2.http.GET

interface ITunesApi {

    @GET("search?term=a&media=movie&;all")
    suspend fun getDefaultMovies(): WrapperListDto<MovieDto>
}