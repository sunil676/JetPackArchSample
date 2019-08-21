package com.sunil.arch.local

import com.sunil.arch.data.DataSet
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class MovieDaoTest : BaseTest() {

    override fun setUp() {
        super.setUp()
        initDataBase()
    }

    @Test
    fun getMovieData() {
        runBlocking {
            val movie = database.movieDao().getTopRatedMovies()
            Assert.assertEquals(1, movie.size)
        }

    }

    private fun initDataBase() {
        runBlocking {
            database.movieDao().save(DataSet.FAKE_MOVIE)
        }
    }
}