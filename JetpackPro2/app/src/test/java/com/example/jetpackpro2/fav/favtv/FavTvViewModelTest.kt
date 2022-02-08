package com.example.jetpackpro2.fav.favtv

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataTvEntity
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
class FavTvViewModelTest  {
    private lateinit var viewModel: FavTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: RepoFilm

    @Mock
    private lateinit var observer: Observer<PagedList<DataTvEntity>>

    @Mock
    private lateinit var pagedList: PagedList<DataTvEntity>

    @Before
    fun setup(){
        viewModel = FavTvViewModel(filmRepository)
    }

    @Test
    fun getFavTvShows(){
        val dummyTvShows = pagedList
        Mockito.`when`(dummyTvShows.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<DataTvEntity>>()
        tv.value = dummyTvShows

        Mockito.`when`(filmRepository.getTvShowFavorite()).thenReturn(tv)
        val tvEntities = viewModel.getTvShowFav().value
        verify(filmRepository).getTvShowFavorite()
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(5, tvEntities?.size)

        viewModel.getTvShowFav().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

    @Test
    fun setFavTvShows(){
        viewModel.setTvShowFav(DataDummy.generateDetailTvShow())
        verify(filmRepository).setTvShowFavorite(DataDummy.generateDetailTvShow(), true)
        verifyNoMoreInteractions(filmRepository)
    }

}