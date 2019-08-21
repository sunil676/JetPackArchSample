package com.sunil.arch.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sunil.arch.data.MovieEntity
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration



@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    // dao
    abstract fun movieDao() : MovieEntityDao

    companion object{
        fun buildDatabase(context: Context) = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "movie.db").build()
    }

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    }
}