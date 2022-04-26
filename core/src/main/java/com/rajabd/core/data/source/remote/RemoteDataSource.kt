package com.rajabd.core.data.source.remote

import android.content.ContentValues.TAG
import android.util.Log
import com.rajabd.core.BuildConfig
import com.rajabd.core.data.source.remote.network.ApiService
import com.rajabd.core.data.source.remote.response.MovieResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    private val apiKey = BuildConfig.API_KEY

    suspend fun getMovies(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(apiKey)
                val movieList = response.results
                if (movieList.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getMovies: $e")
            }
        }.flowOn(Dispatchers.IO)
    }
}
