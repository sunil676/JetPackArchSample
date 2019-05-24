package com.sunil.arch.remote.api

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sunil.arch.remote.repository.Resource
import kotlinx.coroutines.*
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) { result.value =
                Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            fetchFromNetwork()
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    private suspend fun fetchFromNetwork() {
        val apiResponse = createCallAsync().await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        val result = processResponse(apiResponse)
        setValue(Resource.success(result))
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Log.d(NetworkBoundResource::class.java.name, "Resource: " + newValue)
        if (result.value != newValue) result.postValue(newValue)
    }


    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType


    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>
}