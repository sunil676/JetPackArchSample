package com.sunil.arch

import android.app.Application
import com.sunil.arch.injection.appComponent
import org.koin.android.ext.android.startKoin

open class MainApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    open fun initKoin(){
        startKoin(this, provideComponent())
    }

    // PUBLIC API ---
    open fun provideComponent() = appComponent
}