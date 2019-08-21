package com.sunil.arch.injection

import com.sunil.arch.local.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

private const val DATABASE = "DATABASE"

val localModule = module {

    single (DATABASE){ AppDatabase.buildDatabase(androidContext()) }
    factory { (get(DATABASE) as AppDatabase).movieDao() }

}