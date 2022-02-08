package com.example.jetpackpro2.fav.favtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.film.RepoFilm

class FavTvViewModel (private val filmRepository: RepoFilm): ViewModel() {

    fun getTvShowFav(): LiveData<PagedList<DataTvEntity>> = filmRepository.getTvShowFavorite()

    fun setTvShowFav(tvShowEntity: DataTvEntity){
        val newState = !tvShowEntity.favorite
        filmRepository.setTvShowFavorite(tvShowEntity, newState)
    }
}