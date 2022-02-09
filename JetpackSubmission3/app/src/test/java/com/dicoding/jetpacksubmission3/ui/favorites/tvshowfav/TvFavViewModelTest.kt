package com.dicoding.jetpacksubmission3.ui.favorites.tvshowfav

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
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
class TvFavViewModelTest {
    private lateinit var viewModel: TvFavViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup(){
        viewModel = TvFavViewModel(filmRepository)
    }

    @Test
    fun getFavTvShows(){
        val dummyTvShows = pagedList
        Mockito.`when`(dummyTvShows.size).thenReturn(5)
        val tv = MutableLiveData<PagedList<TvShowEntity>>()
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