package com.sunil.arch.injection

import com.sunil.arch.utility.AppConstant

val appComponent= listOf(createRemoteModule(AppConstant.BASEURL), repositoryModule, featureHomeModule, featureDetailModule)