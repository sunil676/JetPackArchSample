package com.sunil.arch.injection

import com.sunil.arch.remote.repository.AppDispatchers
import com.sunil.arch.remote.repository.MovieRepository
import com.sunil.arch.remote.repository.MovieRepositoryImp
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module.module

val repositoryModule = module {
    factory { AppDispatchers(Dispatchers.Main, Dispatchers.IO) }
    factory { MovieRepositoryImp(get(), get ()) as MovieRepository }
}