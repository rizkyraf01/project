package com.dicoding.jetpacksubmission3.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.dicoding.jetpacksubmission3.data.source.local.entity.MoviesEntity
import com.dicoding.jetpacksubmission3.data.source.local.entity.TvShowEntity


@Dao
interface FilmDao {
    //Movies
    @RawQuery(observedEntities = [MoviesEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, MoviesEntity>

    @Query("SELECT * FROM movies_entity WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<MoviesEntity>

    @Query("SELECT * FROM movies_entity WHERE favorite = 1")
    fun getMovieFav(): DataSource.Factory<Int, MoviesEntity>

    //TvShows
    @RawQuery(observedEntities = [TvShowEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE tvId= :tvId")
    fun getTvShowById(tvId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE favorite = 1")
    fun getTvShowFav(): DataSource.Factory<Int, TvShowEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MoviesEntity>)

    @Update
    fun updateMovie(movie: MoviesEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<TvShowEntity>)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)
}