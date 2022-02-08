package com.example.jetpackpro2.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.utils.DataDummy
import com.example.jetpackpro2.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FilmViewModelTest {
    private lateinit var viewModel: FilmViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: RepoFilm

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<DataFilmEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<DataFilmEntity>

    @Before
    fun setup(){
        viewModel = FilmViewModel(filmRepository)
    }

    @Test
    fun getTvShows(){
        val dummyMovieShows = Resource.success(pagedList)
        Mockito.`when`(dummyMovieShows.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<DataFilmEntity>>>()
        movies.value = dummyMovieShows

        Mockito.`when`(filmRepository.getMovies("NEWEST_MOVIE")).thenReturn(movies)
        val moviesEntities = viewModel.getMovies("NEWEST_MOVIE").value?.data
        verify(filmRepository).getMovies("NEWEST_MOVIE")
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(5, moviesEntities?.size)

        viewModel.getMovies("NEWEST_MOVIE").observeForever(observer)
        verify(observer).onChanged(dummyMovieShows)
    }
}