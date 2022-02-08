package com.example.jetpackpro2.fav.favfilm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.film.DataFilmDetailView
import com.example.jetpackpro2.film.RepoFilm
import com.example.jetpackpro2.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import junit.framework.TestCase
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavFilmViewModelTest  {

    private lateinit var viewModel: FavFilmViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: RepoFilm

    @Mock
    private lateinit var observer: Observer<PagedList<DataFilmEntity>>

    @Mock
    private lateinit var pagedList: PagedList<DataFilmEntity>

    @Before
    fun setup(){
        viewModel = FavFilmViewModel(filmRepository)
    }

    @Test
    fun getFavTvShows(){
        val dummyMovies = pagedList
        Mockito.`when`(dummyMovies.size).thenReturn(5)
        val movie = MutableLiveData<PagedList<DataFilmEntity>>()
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