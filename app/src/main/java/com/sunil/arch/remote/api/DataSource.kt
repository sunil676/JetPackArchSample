package com.sunil.arch.remote.api

class DataSource(private val apiService: ApiService){

    fun getMoviesList() = apiService.loadMovies();
}