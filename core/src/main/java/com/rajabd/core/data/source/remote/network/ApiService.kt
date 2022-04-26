package com.rajabd.core.data.source.remote.network

import com.rajabd.core.BuildConfig
import com.rajabd.core.data.source.remote.response.ListMovieResponse
import com.rajabd.core.utils.Constants
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.URL_MOVIE)
    suspend fun getMovies(
        @Query(Constants.API_KEY) apiKey: String = BuildConfig.API_KEY,
    ): ListMovieResponse

}
