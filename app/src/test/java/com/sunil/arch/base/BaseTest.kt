package com.sunil.arch.base

import com.squareup.okhttp.mockwebserver.MockResponse
import com.squareup.okhttp.mockwebserver.MockWebServer
import com.sunil.arch.injection.createRemoteModule
import com.sunil.arch.remote.api.ApiService
import org.junit.After
import org.junit.Before
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest
import java.io.File

private const val DATABASE = "DATABASE"

abstract class BaseTest : KoinTest {

    protected val apiService: ApiService by inject()
    protected lateinit var mockServer: MockWebServer

    @Before
    open fun setUp() {
        this.configureMockServer()
        this.configureDi()
    }

    @After
    open fun tearDown() {
        this.stopMockServer()
        StandAloneContext.stopKoin()

    }

    private fun stopMockServer() {
        mockServer.shutdown()
    }

    private fun configureMockServer() {
        mockServer = MockWebServer()
        mockServer.start()
    }


    private fun configureDi() {
        listOf(createRemoteModule(mockServer.url("/").toString()))
    }

    /* private fun configureLocalModuleTest(context: Context) = module {
         single(DATABASE) {
             Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
                 .allowMainThreadQueries().build()
         }
         factory { (get(DATABASE) as AppDatabase).movieDao() }
     }*/

    fun mockHttpResponse(mockServer: MockWebServer, fileName: String, responseCode: Int) = mockServer.enqueue(
        MockResponse()
            .setResponseCode(responseCode)
            .setBody(getJson(fileName))
    )

    private fun getJson(path: String): String {
        val uri = this.javaClass.classLoader.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }
}