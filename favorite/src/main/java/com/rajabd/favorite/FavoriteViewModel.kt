package com.rajabd.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.domain.usecase.MovieUseCase

class FavoriteViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getFavoriteMovie(): LiveData<List<Movie>> =
        movieUseCase.getFavoriteMovies().asLiveData()

}
