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
            // please check should I fetch from local db or direct from network
            val dbResult = loadFromDb()
            if (shouldFetch(dbResult)){
                // if the db result is null then fetch from network
                try {
                    fetchFromNetwork()
                } catch (e: Exception) {
                    //Log.e("NetworkBoundResource", "An error happened: $e")
                    setValue(Resource.error(e, loadFromDb()))
                }
            }else {
                //Log.d(NetworkBoundResource::class.java.name, "Return data from local database")
                setValue(Resource.success(dbResult))
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    private suspend fun fetchFromNetwork() {
        val apiResponse = createCallAsync().await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        val result = processResponse(apiResponse)
        // save the data into table
        deleteAllResult()
        saveCallResults(result)
        // now you can get the same result form db
        setValue(Resource.success(loadFromDb()))
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        //Log.d(NetworkBoundResource::class.java.name, "Resource: " + newValue)
        if (result.value != newValue) result.postValue(newValue)
    }


    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType


    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType

    @MainThread
    protected abstract fun shouldFetch(data: ResultType?): Boolean

    @WorkerThread
    protected abstract suspend fun saveCallResults(items: ResultType)

    @WorkerThread
    protected abstract suspend fun deleteAllResult()
}