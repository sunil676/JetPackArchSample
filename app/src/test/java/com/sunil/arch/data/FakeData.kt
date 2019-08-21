package com.sunil.arch.data

object FakeData{

    fun createFakeMovies(count :Int) : List<MovieEntity>{
        return (0 until count).map {
            createFakeMovie()
        }
    }

    fun createFakeMovie() : MovieEntity {
        val movieEntity = MovieEntity()
        movieEntity.id = 1
        movieEntity.backdropPath = ""
        movieEntity.isAdult = false
        movieEntity.originalLanguage = "English"
        movieEntity.overview = "this is movies"
        movieEntity.originalTitle = "Movie"
        movieEntity.posterPath = ""
        movieEntity.insertedTime = DataSet.DATE_INSERT
        movieEntity.title = "Movie Name"
        movieEntity.voteAverage = 2.0
        movieEntity.voteCount = 2

        return movieEntity
    }
}