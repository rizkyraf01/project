package com.dicoding.jetpacksubmission3.ui.favorites.tvshowfav

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity

class TvFavViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getTvShowFav(): LiveData<PagedList<TvShowEntity>> = filmRepository.getTvShowFavorite()

    fun setTvShowFav(tvShowEntity: TvShowEntity){
        val newState = !tvShowEntity.favorite
        filmRepository.setTvShowFavorite(tvShowEntity, newState)
    }
}