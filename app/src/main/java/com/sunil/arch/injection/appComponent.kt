package com.sunil.arch.injection

import com.sunil.arch.BuildConfig

val appComponent =
    listOf(createRemoteModule(BuildConfig.URL_API), repositoryModule, featureMainModule, featureDetailModule)