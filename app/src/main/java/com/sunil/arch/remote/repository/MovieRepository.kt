package com.sunil.arch.remote.repository

import androidx.lifecycle.LiveData
import com.sunil.arch.data.MovieEntity

interface MovieRepository{
    suspend fun getMovieList(): LiveData<Resource<List<MovieEntity>>>
}