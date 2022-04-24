package com.rajabd.core.domain.repository

import com.rajabd.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface IMovieRepository {

    fun getAllMovies(sort: String): Flow<com.rajabd.core.data.source.Resource<List<Movie>>>

    fun getFavoriteMovies(): Flow<List<Movie>>

    fun getSearchMovie(title: String): Flow<List<Movie>>

    fun setMovieFavorite(movie: Movie, state: Boolean)
}
