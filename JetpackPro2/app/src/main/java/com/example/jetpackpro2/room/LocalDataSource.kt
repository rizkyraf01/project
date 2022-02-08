package com.example.jetpackpro2.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.utils.SortUtils
import com.example.jetpackpro2.utils.SortUtils.MOVIES_ENTITY
import com.example.jetpackpro2.utils.SortUtils.TV_SHOWS_ENTITY

class LocalDataSource private constructor(private val mFilmDao: FilmDao){

    companion object {

        private var INSTANCE: LocalDataSource? = null

        fun getInstance(filmDao : FilmDao): LocalDataSource {
            if (INSTANCE == null) {
                INSTANCE = LocalDataSource(filmDao)
            }
            return INSTANCE as LocalDataSource
        }
    }


    fun getAllMovies(sort: String): DataSource.Factory<Int, DataFilmEntity> = mFilmDao.getMovies(
        SortUtils.getSortedQuery(sort, MOVIES_ENTITY))
    fun getMoviesById(movieId: Int): LiveData<DataFilmEntity> = mFilmDao.getMovieById(movieId)
    fun getMoviesFav(): DataSource.Factory<Int, DataFilmEntity> = mFilmDao.getMovieFav()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, DataTvEntity> = mFilmDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOWS_ENTITY))
    fun getTvShowById(tvId: Int): LiveData<DataTvEntity> = mFilmDao.getTvShowById(tvId)
    fun getTvShowsFav(): DataSource.Factory<Int, DataTvEntity> = mFilmDao.getTvShowFav()


    fun insertMovies(movies: List<DataFilmEntity>) = mFilmDao.insertMovies(movies)
    fun setMoviesFavorite(movie: DataFilmEntity, newState: Boolean){
        movie.favorite = newState
        mFilmDao.updateMovie(movie)
    }
    fun updateMovie(movie: DataFilmEntity, newState: Boolean) {
        movie.favorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun insertTvShow(tv: List<DataTvEntity>) = mFilmDao.insertTvShows(tv)
    fun setTvShowFavorite(tv: DataTvEntity, newState: Boolean){
        tv.favorite = newState
        mFilmDao.updateTvShow(tv)
    }
    fun updateTvShow(tv: DataTvEntity, newState: Boolean) {
        tv.favorite = newState
        mFilmDao.updateTvShow(tv)
    }

}