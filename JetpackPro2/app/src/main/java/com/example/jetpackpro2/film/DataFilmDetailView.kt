package com.example.jetpackpro2.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.vo.Resource

class DataFilmDetailView(private val filmRepository: RepoFilm): ViewModel()  {
    private lateinit var detailMovie: LiveData<Resource<DataFilmEntity>>
    fun selectFilm(movieId: Int){
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

    fun getMovies(sort: String): LiveData<Resource<PagedList<DataFilmEntity>>> = filmRepository.getMovies(sort)

}