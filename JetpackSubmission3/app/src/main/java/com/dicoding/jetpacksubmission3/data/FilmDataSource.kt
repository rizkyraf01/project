package com.dicoding.jetpacksubmission3.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.source.local.entity.*
import com.dicoding.jetpacksubmission3.vo.Resource

interface FilmDataSource {
    fun getMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>>
    fun getDetailMovies(movieId: Int): LiveData<Resource<MoviesEntity>>

    fun getMoviesFavorite(): LiveData<PagedList<MoviesEntity>>
    fun setMoviesFavorite(movie: MoviesEntity, state: Boolean)

    fun getTvShows(sort: String): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getDetailTvShow(tvId: Int): LiveData<Resource<TvShowEntity>>

    fun getTvShowFavorite(): LiveData<PagedList<TvShowEntity>>
    fun setTvShowFavorite(movie: TvShowEntity, state: Boolean)

}