package com.example.jetpackpro2.fav.favfilm

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.film.RepoFilm

class FavFilmViewModel(private val filmRepository: RepoFilm): ViewModel() {

    fun getMoviesFav(): LiveData<PagedList<DataFilmEntity>> = filmRepository.getMoviesFavorite()

    fun setMoviesFav(moviesEntity: DataFilmEntity){
        val newState = !moviesEntity.favorite
        filmRepository.setMoviesFavorite(moviesEntity, newState)
    }

}