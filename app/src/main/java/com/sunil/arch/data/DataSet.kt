package com.sunil.arch.data

import java.util.*

object DataSet{

    val DATE_INSERT: Date = GregorianCalendar(2019, 5, 12).time
    val FAKE_MOVIE = listOf(
        getMovieEntity(), getMovieEntity()
    )

    fun getMovieEntity() : MovieEntity{
        val movieEntity = MovieEntity()
        movieEntity.id = 1
        movieEntity.backdropPath = ""
        movieEntity.isAdult = false
        movieEntity.originalLanguage = "English"
        movieEntity.overview = "this is movies"
        movieEntity.originalTitle = "Movie"
        movieEntity.posterPath = ""
        movieEntity.insertedTime = DATE_INSERT
        movieEntity.title = "Movie Name"
        movieEntity.voteAverage = 2.0
        movieEntity.voteCount = 2
        return movieEntity
    }
}