package com.rajabd.mymoviesappmade.detail

import androidx.lifecycle.ViewModel
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.domain.usecase.MovieUseCase

class DetailViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun setFavoriteMovie(movie: Movie, newState: Boolean) {
        movieUseCase.setMovieFavorite(movie, newState)
    }
}
