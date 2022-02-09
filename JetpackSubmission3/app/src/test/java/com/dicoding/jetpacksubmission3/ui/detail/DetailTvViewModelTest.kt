package com.dicoding.jetpacksubmission3.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
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
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvViewModelTest {

    private lateinit var viewModel: DetailTvViewModel

    private val dummyTv = DataDummy.generateDetailTvShow()
    private val dummyId = dummyTv.tvId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowEntity>>

    @Mock
    private lateinit var observera: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup(){
        viewModel = DetailTvViewModel(filmRepository)
    }

    @Test
    fun getTv(){
        val dummyMovies= Resource.success(DataDummy.generateDetailTvShow())
        val movie = MutableLiveData<Resource<TvShowEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(filmRepository.getDetailTvShow(dummyId)).thenReturn(movie)
        viewModel.setSelectedTv(dummyId)
        viewModel.getTvShow().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

    @Test
    fun setFavoriteTv() {
        val dummyMovies = Resource.success(DataDummy.generateDetailTvShow())
        val movie = MutableLiveData<Resource<TvShowEntity>>()
        movie.value = dummyMovies

        Mockito.`when`(filmRepository.getDetailTvShow(dummyId)).thenReturn(movie)
        viewModel.setSelectedTv(dummyId)
        viewModel.setFavoriteTvShow()
        verify(filmRepository).setTvShowFavorite(movie.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(observer)
    }


    @Test
    fun getTvShow(){
        val dummyMovieShows = Resource.success(pagedList)
        Mockito.`when`(dummyMovieShows.data?.size).thenReturn(5)
        val movies = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        movies.value = dummyMovieShows

        Mockito.`when`(filmRepository.getTvShows("NEWEST_TV")).thenReturn(movies)
        val moviesEntities = viewModel.getTvShow("NEWEST_TV").value?.data
        verify(filmRepository).getTvShows("NEWEST_TV")
        Assert.assertNotNull(moviesEntities)
        Assert.assertEquals(5, moviesEntities?.size)

        viewModel.getTvShow("NEWEST_TV").observeForever(observera)
        verify(observera).onChanged(dummyMovieShows)
    }

}