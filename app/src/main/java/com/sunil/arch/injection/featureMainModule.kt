package com.sunil.arch.injection

import com.sunil.arch.viewModel.MainViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val featureMainModule = module {
    viewModel { MainViewModel(get(), get()) }
}