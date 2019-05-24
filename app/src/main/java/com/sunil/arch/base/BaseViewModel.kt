package com.sunil.arch.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.sunil.arch.navigation.NavigationController
import com.sunil.arch.utility.Event

abstract class BaseViewModel : ViewModel() {

    // for error handler
    protected val snackBarErrorLiveData = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = snackBarErrorLiveData

    // for navigation
    private val navigationLiveData = MutableLiveData<Event<NavigationController>>()
    val navigation: LiveData<Event<NavigationController>> = navigationLiveData


    fun navigate(directions: NavDirections){
        navigationLiveData.value = Event(NavigationController.To(directions))
    }

}