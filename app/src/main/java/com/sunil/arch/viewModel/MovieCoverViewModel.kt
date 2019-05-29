package com.sunil.arch.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunil.arch.base.BaseViewModel

class MovieCoverViewModel : BaseViewModel() {

    //lateinit var argsImageUrl: String

    private val argsImageUrl = MutableLiveData<String>()
    val imageUrl: LiveData<String> get() = argsImageUrl

    fun loadWhenFragmentViewCreated(imageUrl :String){
        argsImageUrl.value = imageUrl
    }


}