package com.sunil.arch.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.koin.dsl.module.module
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest

private const val DATABASE = "DATABASE"
@RunWith(AndroidJUnit4::class)
abstract class BaseTest: KoinTest {

    protected val database: AppDatabase by inject()

    @Before
    open fun setUp() {
        this.configureDi()
    }

    @After
    open fun tearDown() {
        StandAloneContext.stopKoin()
    }

    // CONFIGURATION
    private fun configureDi() {
        StandAloneContext.startKoin(listOf(configureLocalModuleTest(ApplicationProvider.getApplicationContext<Context>())))
    }

    private fun configureLocalModuleTest(context: Context) = module {
        single(DATABASE) {
            Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
        factory { (get(DATABASE) as AppDatabase).movieDao() }
    }
}