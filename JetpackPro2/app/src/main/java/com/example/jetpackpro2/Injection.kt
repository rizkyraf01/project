package com.example.jetpackpro2

import android.content.Context
import com.example.jetpackpro2.film.RepoFilm
import com.example.jetpackpro2.room.FilmDatabase
import com.example.jetpackpro2.room.LocalDataSource
import com.example.jetpackpro2.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): RepoFilm {
        val database = FilmDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance()
        val localDataSource = LocalDataSource.getInstance(database.filmDao())
        val appExecutors = AppExecutors()

        return RepoFilm.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}