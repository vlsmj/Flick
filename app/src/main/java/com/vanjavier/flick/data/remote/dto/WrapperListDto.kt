package com.vanjavier.flick.data.remote.dto

data class WrapperListDto<T>(
    val resultCount: Int,
    val results: List<T>,
)