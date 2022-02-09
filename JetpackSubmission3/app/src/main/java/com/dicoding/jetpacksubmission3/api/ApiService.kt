package com.dicoding.jetpacksubmission3.api

import com.dicoding.jetpacksubmission3.data.source.remote.response.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ) : Call<MoviesListResponse>

    @GET("movie/{movie_id}")
    fun getMovieDetail(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<MoviesResponse>

    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ) : Call<TvShowListResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<TvShowResponse>
}