package com.dicoding.jetpacksubmission3.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
import com.dicoding.jetpacksubmission3.vo.Resource

class DetailTvViewModel(private val filmRepository: FilmRepository): ViewModel() {

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>

    fun setSelectedTv(tvId: Int){
         detailTvShow = filmRepository.getDetailTvShow(tvId)
    }

    fun getTvShow() = detailTvShow

    fun setFavoriteTvShow(){
        val filmResource = detailTvShow.value
        if(filmResource != null){
            val filmResourceData = filmResource.data

            if(filmResourceData != null){
                val newState = !filmResourceData.favorite
                filmRepository.setTvShowFavorite(filmResourceData, newState)
            }
        }
    }

    fun getTvShow(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> = filmRepository.getTvShows(sort)

}