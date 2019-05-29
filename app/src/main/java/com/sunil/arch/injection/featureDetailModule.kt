package com.sunil.arch.injection

import com.sunil.arch.viewModel.DetailViewModel
import com.sunil.arch.viewModel.MovieCoverViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureDetailModule = module {
    viewModel { DetailViewModel() }
    viewModel { MovieCoverViewModel() }
}