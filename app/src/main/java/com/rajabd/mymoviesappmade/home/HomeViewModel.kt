package com.rajabd.mymoviesappmade.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rajabd.core.data.source.Resource
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.domain.usecase.MovieUseCase

class HomeViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<List<Movie>>> =
        movieUseCase.getAllMovies(sort).asLiveData()
}
