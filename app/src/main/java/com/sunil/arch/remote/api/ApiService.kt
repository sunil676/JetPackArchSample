package com.sunil.arch.remote.api

import com.sunil.arch.BuildConfig
import com.sunil.arch.data.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface ApiService {

    @GET("movie/popular?api_key=${BuildConfig.API_KEY}")
    fun loadMovies(): Deferred<MoviesResponse>
}