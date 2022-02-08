package com.example.jetpackpro2.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import androidx.sqlite.db.SimpleSQLiteQuery
import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity

@Dao
interface FilmDao {
    //Movies
    @RawQuery(observedEntities = [DataFilmEntity::class])
    fun getMovies(query: SimpleSQLiteQuery): DataSource.Factory<Int, DataFilmEntity>

    @Query("SELECT * FROM movies_entity WHERE movieId = :movieId")
    fun getMovieById(movieId: Int): LiveData<DataFilmEntity>

    @Query("SELECT * FROM movies_entity WHERE favorite = 1")
    fun getMovieFav(): DataSource.Factory<Int, DataFilmEntity>

    //TvShows
    @RawQuery(observedEntities = [DataTvEntity::class])
    fun getTvShows(query: SimpleSQLiteQuery): DataSource.Factory<Int, DataTvEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE tvId= :tvId")
    fun getTvShowById(tvId: Int): LiveData<DataTvEntity>

    @Query("SELECT * FROM tv_shows_entity WHERE favorite = 1")
    fun getTvShowFav(): DataSource.Factory<Int, DataTvEntity>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<DataFilmEntity>)

    @Update
    fun updateMovie(movie: DataFilmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(tvShows: List<DataTvEntity>)

    @Update
    fun updateTvShow(tvShow: DataTvEntity)
}