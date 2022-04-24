package com.rajabd.mymoviesappmade.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.rajabd.core.domain.model.Movie
import com.rajabd.core.domain.usecase.MovieUseCase

class SearchViewModel(private val movieUseCase: MovieUseCase) : ViewModel() {

    fun searchForItems(title: String) : LiveData<List<Movie>> {
        return movieUseCase.getSearchMovies(title).asLiveData()
    }
}
