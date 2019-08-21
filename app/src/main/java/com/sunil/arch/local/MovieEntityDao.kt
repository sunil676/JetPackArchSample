package com.sunil.arch.local

import androidx.room.Dao
import androidx.room.Query
import com.sunil.arch.data.MovieEntity
import java.util.*

@Dao
abstract class MovieEntityDao : BaseDao<MovieEntity>(){

    suspend fun save(movieEntity: MovieEntity) {
        // update the current date
        insert(movieEntity.apply { insertedTime = Date() })
    }

    suspend fun save(movieEntities : List<MovieEntity>) {
        // update the current date
        insert(movieEntities.apply { forEach { it.insertedTime = Date() } })
    }

    @Query("SELECT * FROM movieentity ORDER BY voteAverage ASC LIMIT 10")
    abstract suspend fun getTopRatedMovies() : List<MovieEntity>

    @Query("DELETE FROM movieentity")
    abstract suspend fun deleteAll()

}