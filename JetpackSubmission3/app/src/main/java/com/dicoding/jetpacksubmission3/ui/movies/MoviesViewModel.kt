package com.dicoding.jetpacksubmission3.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.vo.Resource

class MoviesViewModel(private val filmRepository: FilmRepository) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<PagedList<MoviesEntity>>> = filmRepository.getMovies(sort)
}