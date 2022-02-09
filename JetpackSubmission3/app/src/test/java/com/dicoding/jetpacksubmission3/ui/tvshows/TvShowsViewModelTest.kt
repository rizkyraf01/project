package com.dicoding.jetpacksubmission3.ui.tvshows

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import androidx.lifecycle.Observer
import com.dicoding.jetpacksubmission3.data.FilmRepository
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
import com.dicoding.jetpacksubmission3.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class  TvShowsViewModelTest {

    private lateinit var viewModel: TvShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: FilmRepository

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

    @Before
    fun setup(){
        viewModel = TvShowsViewModel(filmRepository)
    }

    @Test
    fun getTvShows(){
        val dummyTvShows = Resource.success(pagedList)
        `when`(dummyTvShows.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tv.value = dummyTvShows

        `when`(filmRepository.getTvShows("NEWEST_TV")).thenReturn(tv)
        val tvEntities = viewModel.getTvShow("NEWEST_TV").value?.data
        verify(filmRepository).getTvShows("NEWEST_TV")
        assertNotNull(tvEntities)
        assertEquals(5, tvEntities?.size)

        viewModel.getTvShow("NEWEST_TV").observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
    
}