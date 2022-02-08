package com.example.jetpackpro2.film

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.utils.DataDummy
import com.example.jetpackpro2.vo.Resource
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
class DataFIlmDetailViewTest {
    private lateinit var viewModel: DataFilmDetailView

    private val dummyMovies = DataDummy.generateDetailMovies()
    private val dummyId = dummyMovies.movieId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: RepoFilm

    @Mock
    private lateinit var observer: Observer<Resource<DataFilmEntity>>

    @Mock
    private lateinit var observera: Observer<Resource<PagedList<DataFilmEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<DataFilmEntity>

    @Before
    fun setup(){
        viewModel = DataFilmDetailView(filmRepository)
    }

    @Test
    fun getMovie(){
        val dummyMovies= Resource.success(DataDummy.generateDetailMovies())
        val movie = MutableLiveData<Resource<DataFilmEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(filmRepository.getDetailMovies(dummyId)).thenReturn(movie)
        viewModel.selectFilm(dummyId)
        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyMovies = Resource.success(DataDummy.generateDetailMovies())
        val movie = MutableLiveData<Resource<DataFilmEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(filmRepository.getDetailMovies(dummyId)).thenReturn(movie)
        viewModel.selectFilm(dummyId)
        viewModel.setFavoriteMovie()
        verify(filmRepository).setMoviesFavorite(movie.value!!.data as DataFilmEntity, true)
        verifyNoMoreInteractions(observer)
    }


    @Test
    fun getMovies(){
        val dummyMovieShows = Resource.success(pagedList)
        Mockito.`when`(dummyMovieShows.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<DataFilmEntity>>>()
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