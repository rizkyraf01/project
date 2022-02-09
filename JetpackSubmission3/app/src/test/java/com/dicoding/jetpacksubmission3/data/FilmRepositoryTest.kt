package com.dicoding.jetpacksubmission3.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.dicoding.jetpacksubmission3.data.source.local.LocalDataSource
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity
import com.dicoding.jetpacksubmission3.data.source.remote.RemoteDataSource
import com.dicoding.jetpacksubmission3.utils.*
import com.dicoding.jetpacksubmission3.vo.Resource
import com.nhaarman.mockitokotlin2.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class FilmRepositoryTest {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val fakeFilmRepository = FakeFilmRepository(remote, local, appExecutors)
    private val testExecutor = AppExecutors(TestExecutor(), TestExecutor(), TestExecutor())

    private val moviesResponses = DataDummy.generateRemoteMovies()
    private val movieId = moviesResponses[0].id

    private val movieDetailResponses = DataDummy.generateRemoteDummyMovies()

    private val tvResponses = DataDummy.generateRemoteTvShow()
    private val tvId = tvResponses[0].id

    private val tvDetailResponses = DataDummy.generateRemoteDummyTvShow()


    @Test
    fun getMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getAllMovies("NEWEST_MOVIE")).thenReturn(dataSourceFactory)
        fakeFilmRepository.getMovies("NEWEST_MOVIE")

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getAllMovies("NEWEST_MOVIE")
        assertNotNull(moviesEntities.data)
        assertEquals(moviesResponses.size.toLong(), moviesEntities.data?.size?.toLong())
    }


    @Test
    fun getDetailMovies() {
        val dummyMovies= MutableLiveData<MoviesEntity>()
        dummyMovies.value = DataDummy.generateDetailMovies()
        `when`(local.getMoviesById(movieId)).thenReturn(dummyMovies)

        val filmEntities = LiveDataTestUtil.getValue(fakeFilmRepository.getDetailMovies(movieId))
        verify(local).getMoviesById(movieId)
        assertNotNull(filmEntities.data)
        assertEquals(movieDetailResponses.id, filmEntities.data?.movieId)
    }

    @Test
    fun getMoviesFavorite(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MoviesEntity>
        `when`(local.getMoviesFav()).thenReturn(dataSourceFactory)
        fakeFilmRepository.getMoviesFavorite()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateMovies()))
        verify(local).getMoviesFav()
        assertNotNull(moviesEntities)
        assertEquals(moviesResponses.size, moviesEntities.data?.size)
    }

    @Test
    fun setMoviesFavorite(){
        val dummyFilm = DataDummy.generateMovies()[0].copy(favorite = false)
        `when`(appExecutors.diskIO()).thenReturn(testExecutor.diskIO())
        doNothing().`when`(local).setMoviesFavorite(dummyFilm, true)
        fakeFilmRepository.setMoviesFavorite(dummyFilm, true)

        verify(local, times(1)).setMoviesFavorite(dummyFilm,true)
    }

    @Test
    fun getTvShow() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getAllTvShows("NEWEST_TV")).thenReturn(dataSourceFactory)
        fakeFilmRepository.getTvShows("NEWEST_TV")

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShow()))
        verify(local).getAllTvShows("NEWEST_TV")
        assertNotNull(moviesEntities.data)
        assertEquals(tvResponses.size.toLong(), moviesEntities.data?.size?.toLong())
    }


    @Test
    fun getDetailTvShow() {
        val dummyMovies= MutableLiveData<TvShowEntity>()
        dummyMovies.value = DataDummy.generateDetailTvShow()
        `when`(local.getTvShowById(tvId)).thenReturn(dummyMovies)

        val filmEntities = LiveDataTestUtil.getValue(fakeFilmRepository.getDetailTvShow(tvId))
        verify(local).getTvShowById(tvId)
        assertNotNull(filmEntities.data)
        assertEquals(tvDetailResponses.id, filmEntities.data?.tvId)
    }

    @Test
    fun getTvFavorite(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShowsFav()).thenReturn(dataSourceFactory)
        fakeFilmRepository.getTvShowFavorite()

        val moviesEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateTvShow()))
        verify(local).getTvShowsFav()
        assertNotNull(moviesEntities)
        assertEquals(tvResponses.size, moviesEntities.data?.size)
    }

    @Test
    fun setTvFavorite(){
        val dummyFilm = DataDummy.generateTvShow()[0].copy(favorite = false)
        `when`(appExecutors.diskIO()).thenReturn(testExecutor.diskIO())
        doNothing().`when`(local).setTvShowFavorite(dummyFilm, true)
        fakeFilmRepository.setTvShowFavorite(dummyFilm, true)

        verify(local, times(1)).setTvShowFavorite(dummyFilm,true)
    }


}