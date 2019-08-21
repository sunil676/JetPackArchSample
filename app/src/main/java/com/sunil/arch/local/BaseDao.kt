package com.sunil.arch.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

abstract class BaseDao<T>{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(movieEntities : List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract suspend fun insert(movieEntity: T)


}