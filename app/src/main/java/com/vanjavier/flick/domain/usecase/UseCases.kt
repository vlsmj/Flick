package com.vanjavier.flick.domain.usecase

data class UseCases(
    val getAllMoviesUseCase: GetAllMoviesUseCase,
    val getAllFavoriteMoviesUseCase: GetAllFavoriteMoviesUseCase,
    val favoriteMovieUseCase: FavoriteMovieUseCase,
    val unFavoriteMovieUseCase: UnFavoriteMovieUseCase,
    val searchMoviesUseCase: SearchMoviesUseCase,
)