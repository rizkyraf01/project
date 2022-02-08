package com.example.jetpackpro2.film

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.vo.Resource

class FilmViewModel(private val filmRepository: RepoFilm) : ViewModel() {

    fun getMovies(sort: String): LiveData<Resource<PagedList<DataFilmEntity>>> = filmRepository.getMovies(sort)
}