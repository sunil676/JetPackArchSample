package com.sunil.arch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sunil.arch.R
import com.sunil.arch.base.BaseViewModel
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.remote.repository.MovieRepository
import com.sunil.arch.remote.repository.AppDispatchers
import com.sunil.arch.remote.repository.Resource
import com.sunil.arch.ui.main.MainFragmentDirections
import com.sunil.arch.utility.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(val movieRepository: MovieRepository, val appDispatchers: AppDispatchers) : BaseViewModel() {

    private var movieListLiveData: LiveData<Resource<List<MovieEntity>>> = MutableLiveData()
    private val movies = MediatorLiveData<Resource<List<MovieEntity>>>()
    val movie: LiveData<Resource<List<MovieEntity>>> get() = movies

    fun getMovieList() {
        viewModelScope.launch(appDispatchers.main){
            movies.removeSource(movieListLiveData)
            withContext(appDispatchers.io){
                movieListLiveData = movieRepository.getMovieList()
            }
            movies.addSource(movieListLiveData){
                movies.value = it
                if (it.status == Resource.Status.ERROR){
                    snackBarErrorLiveData.value = Event(R.string.an_error_happened)
                }
            }
        }
    }

    fun userClicksOnItem(movieEntity: MovieEntity) =
        navigate(MainFragmentDirections.actionMainFragmentToDetailFragment(movieEntity))
}