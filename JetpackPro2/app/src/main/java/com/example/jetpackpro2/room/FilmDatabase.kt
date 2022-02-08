package com.example.jetpackpro2.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity

@Database(entities = [DataFilmEntity::class, DataTvEntity::class],
    version = 1,
    exportSchema = false)

abstract class FilmDatabase: RoomDatabase() {

    abstract fun filmDao(): FilmDao

    companion object {

        @Volatile
        private var INSTANCE: FilmDatabase? = null

        fun getInstance(context: Context): FilmDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    FilmDatabase::class.java,
                    "Film.db"
                ).build().apply {
                    INSTANCE = this
                }
            }
    }

}