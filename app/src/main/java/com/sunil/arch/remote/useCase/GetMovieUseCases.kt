package com.sunil.arch.remote.useCase

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.remote.repository.MovieRepository
import com.sunil.arch.remote.repository.Resource


/**
 * Use case that gets a [Resource][List][User] from [MovieRepository]
 * and makes some specific logic actions on it.
 *
 * In this Use Case, I'm just doing nothing... ¯\_(ツ)_/¯
 */
class GetMovieUseCases(private val repository: MovieRepository) {

    suspend operator fun invoke(): LiveData<Resource<List<MovieEntity>>> {
        return Transformations.map(repository.getMovieList()) {
            it // Place here your specific logic actions
        }
    }
}