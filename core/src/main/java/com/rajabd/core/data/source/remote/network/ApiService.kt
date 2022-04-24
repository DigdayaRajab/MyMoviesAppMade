package com.rajabd.core.data.source.remote.network

import com.rajabd.core.data.source.remote.response.ListMovieResponse
import com.rajabd.core.utils.NetworkInfo.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("movie")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY
    ): ListMovieResponse
}
