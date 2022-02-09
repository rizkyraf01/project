package com.dicoding.jetpacksubmission3.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
import com.dicoding.jetpacksubmission3.data.source.local.room.FilmDao
import com.dicoding.jetpacksubmission3.utils.SortUtils
import com.dicoding.jetpacksubmission3.utils.SortUtils.MOVIES_ENTITY
import com.dicoding.jetpacksubmission3.utils.SortUtils.TV_SHOWS_ENTITY


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


    fun getAllMovies(sort: String): DataSource.Factory<Int, MoviesEntity> = mFilmDao.getMovies(SortUtils.getSortedQuery(sort, MOVIES_ENTITY))
    fun getMoviesById(movieId: Int): LiveData<MoviesEntity> = mFilmDao.getMovieById(movieId)
    fun getMoviesFav(): DataSource.Factory<Int, MoviesEntity> = mFilmDao.getMovieFav()

    fun getAllTvShows(sort: String): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getTvShows(SortUtils.getSortedQuery(sort, TV_SHOWS_ENTITY))
    fun getTvShowById(tvId: Int): LiveData<TvShowEntity> = mFilmDao.getTvShowById(tvId)
    fun getTvShowsFav(): DataSource.Factory<Int, TvShowEntity> = mFilmDao.getTvShowFav()


    fun insertMovies(movies: List<MoviesEntity>) = mFilmDao.insertMovies(movies)
    fun setMoviesFavorite(movie: MoviesEntity, newState: Boolean){
        movie.favorite = newState
        mFilmDao.updateMovie(movie)
    }
    fun updateMovie(movie: MoviesEntity, newState: Boolean) {
        movie.favorite = newState
        mFilmDao.updateMovie(movie)
    }

    fun insertTvShow(tv: List<TvShowEntity>) = mFilmDao.insertTvShows(tv)
    fun setTvShowFavorite(tv: TvShowEntity, newState: Boolean){
        tv.favorite = newState
        mFilmDao.updateTvShow(tv)
    }
    fun updateTvShow(tv: TvShowEntity, newState: Boolean) {
        tv.favorite = newState
        mFilmDao.updateTvShow(tv)
    }

}