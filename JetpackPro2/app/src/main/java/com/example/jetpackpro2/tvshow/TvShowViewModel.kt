package com.example.jetpackpro2.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.film.RepoFilm
import com.example.jetpackpro2.vo.Resource

class TvShowViewModel(private val filmRepository: RepoFilm): ViewModel() {

    fun getTvShow(sort: String): LiveData<Resource<PagedList<DataTvEntity>>> = filmRepository.getTvShows(sort)

}