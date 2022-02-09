package com.dicoding.jetpacksubmission3.ui.favorites.moviefav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieFavViewModelTest {

    private lateinit var viewModel: MovieFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MoviesEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setup(){
        viewModel = MovieFavViewModel(filmRepository)
    }

    @Test
    fun getFavTvShows(){
        val dummyMovies = pagedList
        Mockito.`when`(dummyMovies.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<MoviesEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(filmRepository.getMoviesFavorite()).thenReturn(movie)
        val movieEntities = viewModel.getMoviesFav().value
        verify(filmRepository).getMoviesFavorite()
        Assert.assertNotNull(movieEntities)
        Assert.assertEquals(5, movieEntities?.size)

        viewModel.getMoviesFav().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavTvShows(){
        viewModel.setMoviesFav(DataDummy.generateDetailMovies())
        verify(filmRepository).setMoviesFavorite(DataDummy.generateDetailMovies(), true)
        verifyNoMoreInteractions(filmRepository)
    }

}