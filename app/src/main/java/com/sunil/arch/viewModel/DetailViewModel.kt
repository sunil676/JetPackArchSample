package com.sunil.arch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunil.arch.base.BaseViewModel
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.ui.detail.DetailFragmentDirections

open class DetailViewModel : BaseViewModel() {

    private val argsMovieEntity = MutableLiveData<MovieEntity>()
    val movieEntity: LiveData<MovieEntity> get() = argsMovieEntity


    fun loadDataWhenActivityStarts(movieEntity: MovieEntity) {
        argsMovieEntity.value = movieEntity
    }

    fun userClicksOnAvatarImage(movieEntity: MovieEntity) =
        navigate(DetailFragmentDirections.actionDetailFragmentToMovieCoverFragment(movieEntity.posterPath))

}