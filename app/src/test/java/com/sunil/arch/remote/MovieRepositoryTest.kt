package com.sunil.arch.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.sunil.arch.data.FakeData
import com.sunil.arch.data.MovieEntity
import com.sunil.arch.data.MoviesResponse
import com.sunil.arch.local.MovieEntityDao
import com.sunil.arch.remote.api.DataSource
import com.sunil.arch.remote.repository.MovieRepository
import com.sunil.arch.remote.repository.MovieRepositoryImp
import com.sunil.arch.remote.repository.Resource
import io.mockk.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class MovieRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesMainDispatcherRule = CoroutinesMainDispatcherRule()

    private lateinit var observer: Observer<Resource<List<MovieEntity>>>
    private lateinit var movieRepository: MovieRepository
    private val dataSource = mockk<DataSource>()
    private var movieDao = mockk<MovieEntityDao>(relaxed = true)


    @Before
    fun setUp() {
        observer = mockk(relaxed = true)
        movieRepository = MovieRepositoryImp(dataSource, movieDao)
    }

    @Test
    fun get_top_movie_list_if_internet_is_available() {
        val exception = Exception("Internet")
        every { dataSource.getMoviesList() } throws exception
        coEvery { movieDao.getTopRatedMovies() } returns listOf()

        runBlocking {
            movieRepository.getMovieList().observeForever(observer)
        }

        verifyOrder {
            observer.onChanged(Resource.loading(null)) // no loading
            //observer.onChanged(Resource.loading(listOf()))  // empty data then load from db
            observer.onChanged(Resource.error(exception, listOf())) // Retrofit error 403
        }
        confirmVerified(observer)

    }

    @Test
    fun get_the_movie_from_the_network() {
        val fakeMovie = FakeData.createFakeMovies(5)
        //every { dataSource.getMoviesList() } returns fakeMovie.get(0)
        coEvery { movieDao.getTopRatedMovies() } returns fakeMovie

        runBlocking {
            movieRepository.getMovieList().observeForever(observer)
        }

        verify {
            observer.onChanged(Resource.loading(null))
            observer.onChanged(Resource.success(fakeMovie))
        }
        confirmVerified(observer)
    }

}

private infix fun <T, B> MockKStubScope<T, B>.returns(moveResponse: MoviesResponse) {
    moveResponse.results = FakeData.createFakeMovies(5)
}
