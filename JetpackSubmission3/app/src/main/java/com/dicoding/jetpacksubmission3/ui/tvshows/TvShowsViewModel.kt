package com.dicoding.jetpacksubmission3.ui.tvshows

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
import com.dicoding.jetpacksubmission3.vo.Resource

class TvShowsViewModel(private val filmRepository: FilmRepository): ViewModel() {

    fun getTvShow(sort: String): LiveData<Resource<PagedList<TvShowEntity>>> = filmRepository.getTvShows(sort)

}