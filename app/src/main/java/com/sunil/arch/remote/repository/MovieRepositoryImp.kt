package com.sunil.arch.remote.repository

import androidx.lifecycle.LiveData
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.data.MoviesResponse
import com.sunil.arch.remote.api.ApiService
import com.sunil.arch.remote.api.NetworkBoundResource
import kotlinx.coroutines.Deferred

class MovieRepositoryImp(val apiService: ApiService) : MovieRepository{

    override suspend fun getMovieList(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){

            override fun processResponse(response: MoviesResponse): List<MovieEntity> {
                    return response.results!!
            }

            override fun createCallAsync(): Deferred<MoviesResponse> {
                return apiService.loadMovies();
            }

        }.build().asLiveData();
    }

}