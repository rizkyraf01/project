package com.dicoding.jetpacksubmission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.vo.Resource

class DetailMoviesViewModel(private val filmRepository: FilmRepository): ViewModel() {

    private lateinit var detailMovie: LiveData<Resource<MoviesEntity>>

    fun setSelectedMovie(movieId: Int){
         detailMovie = filmRepository.getDetailMovies(movieId)
    }

    fun getMovie() = detailMovie

    fun setFavoriteMovie(){
        val filmResource = detailMovie.value
        if(filmResource != null){
            val filmResourceData = filmResource.data

            if(filmResourceData != null){
                val newState = !filmResourceData.favorite
                filmRepository.setMoviesFavorite(filmResourceData, newState)
            }
        }
    }

    fun getMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>> = filmRepository.getMovies(sort)


}