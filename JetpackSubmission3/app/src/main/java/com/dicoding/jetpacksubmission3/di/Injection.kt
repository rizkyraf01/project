package com.dicoding.jetpacksubmission3.di

import android.content.Context
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.LocalDataSource
import com.dicoding.jetpacksubmission3.data.source.local.room.FilmDatabase
import com.dicoding.jetpacksubmission3.data.source.remote.RemoteDataSource
import com.dicoding.jetpacksubmission3.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): FilmRepository {
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return FilmRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}