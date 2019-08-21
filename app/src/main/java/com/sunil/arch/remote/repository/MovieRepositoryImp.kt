package com.sunil.arch.remote.repository

import androidx.lifecycle.LiveData
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.data.MoviesResponse
import com.sunil.arch.local.MovieEntityDao
import com.sunil.arch.remote.api.ApiService
import com.sunil.arch.remote.api.DataSource
import com.sunil.arch.remote.api.NetworkBoundResource
import kotlinx.coroutines.Deferred

class MovieRepositoryImp(val dataSource: DataSource, val movieEntityDao : MovieEntityDao) : MovieRepository{

    override suspend fun getMovieList(): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, MoviesResponse>(){
            override suspend fun deleteAllResult() {
                // not currently Looking for delete, Because we used the insert/replace feature of room.
                //movieEntityDao.deleteAll()
            }

            override fun processResponse(response: MoviesResponse): List<MovieEntity> {
                    return response.results!!
            }

            override fun createCallAsync(): Deferred<MoviesResponse> {
                return dataSource.getMoviesList();
            }

            override fun shouldFetch(data: List<MovieEntity>?): Boolean {
                return data == null || data.isEmpty()
            }

            override suspend fun loadFromDb(): List<MovieEntity> {
               return movieEntityDao.getTopRatedMovies()
            }

            override suspend fun saveCallResults(items: List<MovieEntity>) {
                return movieEntityDao.save(items)
            }

        }.build().asLiveData();
    }

}