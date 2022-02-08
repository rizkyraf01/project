package com.example.jetpackpro2.film

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity

interface FilmDataSource {
    fun getMovies(sort: String): LiveData<com.example.jetpackpro2.vo.Resource<PagedList<DataFilmEntity>>>
    fun getDetailMovies(movieId: Int): LiveData<com.example.jetpackpro2.vo.Resource<DataFilmEntity>>
    fun getMoviesFavorite(): LiveData<PagedList<DataFilmEntity>>
    fun setMoviesFavorite(movie: DataFilmEntity, state: Boolean)
    fun getTvShows(sort: String): LiveData<com.example.jetpackpro2.vo.Resource<PagedList<DataTvEntity>>>
    fun getDetailTvShow(tvId: Int): LiveData<com.example.jetpackpro2.vo.Resource<DataTvEntity>>
    fun getTvShowFavorite(): LiveData<PagedList<DataTvEntity>>
    fun setTvShowFavorite(movie: DataTvEntity, state: Boolean)
}