package com.sunil.arch.injection

import com.sunil.arch.remote.useCase.GetMovieUseCases
import com.sunil.arch.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureMainModule = module {
    factory { GetMovieUseCases(get()) }
    viewModel { MainViewModel(get(), get()) }
}