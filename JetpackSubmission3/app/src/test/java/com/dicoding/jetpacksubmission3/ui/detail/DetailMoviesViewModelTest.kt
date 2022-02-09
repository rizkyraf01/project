package com.dicoding.jetpacksubmission3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.utils.DataDummy
import com.dicoding.jetpacksubmission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMoviesViewModelTest {
    private lateinit var viewModel: DetailMoviesViewModel

    private val dummyMovies = DataDummy.generateDetailMovies()
    private val dummyId = dummyMovies.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<MoviesEntity>>

    @Mock
    private lateinit var observera: Observer<Resource<PagedList<MoviesEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MoviesEntity>

    @Before
    fun setup(){
        viewModel = DetailMoviesViewModel(filmRepository)
    }

    @Test
    fun getMovie(){
        val dummyMovies= Resource.success(DataDummy.generateDetailMovies())
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyMovies

        `when` (filmRepository.getDetailMovies(dummyId)).thenReturn(movie)
        viewModel.setSelectedMovie(dummyId)
        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyMovies = Resource.success(DataDummy.generateDetailMovies())
        val movie = MutableLiveData<Resource<MoviesEntity>>()
        movie.value = dummyMovies

        `when`(filmRepository.getDetailMovies(dummyId)).thenReturn(movie)
        viewModel.setSelectedMovie(dummyId)
        viewModel.setFavoriteMovie()
        verify(filmRepository).setMoviesFavorite(movie.value!!.data as MoviesEntity, true)
        verifyNoMoreInteractions(observer)
    }


    @Test
    fun getMovies(){
        val dummyMovieShows = Resource.success(pagedList)
        Mockito.`when`(dummyMovieShows.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<MoviesEntity>>>()
        movies.value = dummyMovieShows

        Mockito.`when`(filmRepository.getMovies("NEWEST_MOVIE")).thenReturn(movies)
        val moviesEntities = viewModel.getMovies("NEWEST_MOVIE").value?.data
        verify(filmRepository).getMovies("NEWEST_MOVIE")
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(5, moviesEntities?.size)

        viewModel.getMovies("NEWEST_MOVIE").observeForever(observera)
        verify(observera).onChanged(dummyMovieShows)
    }
}