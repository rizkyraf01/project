package com.example.jetpackpro2.repo

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.jetpackpro2.RemoteDataSource
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.room.LocalDataSource
import com.example.jetpackpro2.utils.*
import com.example.jetpackpro2.vo.Resource
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito


class RepoFilmTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)
    private val appExecutors = Mockito.mock(AppExecutors::class.java)
    private val fakeFilmRepository = RepoFilmm(remote, local, appExecutors)
    private val testExecutor = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())

    private val moviesResponses = DataDummy.generateRemoteMovies()
    private val movieId = moviesResponses[0].id

    private val movieDetailResponses = DataDummy.generateRemoteDummyMovies()

    private val tvResponses = DataDummy.generateRemoteTvShow()
    private val tvId = tvResponses[0].id

    private val tvDetailResponses = DataDummy.generateRemoteDummyTvShow()


    @Test
    fun getMovies() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataFilmEntity>
        Mockito.`when`(local.getAllMovies("NEWEST_MOVIE")).thenReturn(dataSourceFactory)
        fakeFilmRepository.getMovies("NEWEST_MOVIE")

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getAllMovies("NEWEST_MOVIE")
        Assert.assertNotNull(moviesEntities.data)
        assertEquals(moviesResponses.size.toLong(), moviesEntities.data?.size?.toLong())
    }




    @Test
    fun getMoviesFavorite(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataFilmEntity>
        Mockito.`when`(local.getMoviesFav()).thenReturn(dataSourceFactory)
        fakeFilmRepository.getMoviesFavorite()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getMoviesFav()
        Assert.assertNotNull(moviesEntities)
        assertEquals(moviesResponses.size, moviesEntities.data?.size)
    }

    @Test
    fun setMoviesFavorite(){
        val dummyFilm = DataDummy.generateMovies()[0].copy(favorite = false)
        Mockito.`when`(appExecutors.diskIO()).thenReturn(testExecutor.diskIO())
        doNothing().`when`(local).setMoviesFavorite(dummyFilm, true)
        fakeFilmRepository.setMoviesFavorite(dummyFilm, true)

        verify(local, times(1)).setMoviesFavorite(dummyFilm,true)
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataTvEntity>
        Mockito.`when`(local.getAllTvShows("NEWEST_TV")).thenReturn(dataSourceFactory)
        fakeFilmRepository.getTvShows("NEWEST_TV")

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShow()))
        verify(local).getAllTvShows("NEWEST_TV")
        Assert.assertNotNull(moviesEntities.data)
        assertEquals(tvResponses.size.toLong(), moviesEntities.data?.size?.toLong())
    }


    @Test
    fun getTvFavorite(){
        val dataSourceFactory = Mockito.mock(DataSource.Factory::class.java) as DataSource.Factory<Int, DataTvEntity>
        Mockito.`when`(local.getTvShowsFav()).thenReturn(dataSourceFactory)
        fakeFilmRepository.getTvShowFavorite()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShow()))
        verify(local).getTvShowsFav()
        Assert.assertNotNull(moviesEntities)
        assertEquals(tvResponses.size, moviesEntities.data?.size)
    }

    @Test
    fun setTvFavorite(){
        val dummyFilm = DataDummy.generateTvShow()[0].copy(favorite = false)
        Mockito.`when`(appExecutors.diskIO()).thenReturn(testExecutor.diskIO())
        doNothing().`when`(local).setTvShowFavorite(dummyFilm, true)
        fakeFilmRepository.setTvShowFavorite(dummyFilm, true)

        verify(local, times(1)).setTvShowFavorite(dummyFilm,true)
    }

}