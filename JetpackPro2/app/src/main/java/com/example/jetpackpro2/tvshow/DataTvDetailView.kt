package com.example.jetpackpro2.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.film.RepoFilm
import com.example.jetpackpro2.vo.Resource

class DataTvDetailView (private val filmRepository: RepoFilm): ViewModel() {
    private lateinit var detailTvShow: LiveData<Resource<DataTvEntity>>
    fun setSelectTv(tvId: Int){
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

    fun getTvShow(sort: String): LiveData<Resource<PagedList<DataTvEntity>>> = filmRepository.getTvShows(sort)
}