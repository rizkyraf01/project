package com.example.jetpackpro2.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.film.RepoFilm
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
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var filmRepository: RepoFilm

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<DataTvEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<DataTvEntity>

    @Before
    fun setup(){
        viewModel = TvShowViewModel(filmRepository)
    }

    @Test
    fun getTvShows(){
        val dummyTvShows = Resource.success(pagedList)
        Mockito.`when`(dummyTvShows.data?.size).thenReturn(5)
        val tv = MutableLiveData<Resource<PagedList<DataTvEntity>>>()
        tv.value = dummyTvShows

        Mockito.`when`(filmRepository.getTvShows("NEWEST_TV")).thenReturn(tv)
        val tvEntities = viewModel.getTvShow("NEWEST_TV").value?.data
        verify(filmRepository).getTvShows("NEWEST_TV")
        Assert.assertNotNull(tvEntities)
        Assert.assertEquals(5, tvEntities?.size)

        viewModel.getTvShow("NEWEST_TV").observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }

}