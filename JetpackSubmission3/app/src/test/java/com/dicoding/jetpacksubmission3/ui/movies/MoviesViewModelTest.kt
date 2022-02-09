package com.dicoding.jetpacksubmission3.ui.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.vo.Resource
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
class MoviesViewModelTest {

    private lateinit var viewModel: MoviesViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setup(){
        viewModel = MoviesViewModel(filmRepository)
    }

    @Test
    fun getTvShows(){
        val dummyMovieShows = Resource.success(pagedList)
        Mockito.`when`(dummyMovieShows.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
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