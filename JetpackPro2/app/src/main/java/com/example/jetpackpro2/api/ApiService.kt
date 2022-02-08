package com.example.jetpackpro2.api

import com.example.jetpackpro2.film.ResponseFilm
import com.example.jetpackpro2.film.ResponseFilmList
import com.example.jetpackpro2.tvshow.ResponseTv
import com.example.jetpackpro2.tvshow.ResponseTvList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String
    ) : Call<ResponseFilmList>
    @GET("movie/{id}")
    fun getMovieDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<ResponseFilm>
    @GET("discover/tv")
    fun getTvShows(
        @Query("api_key") apiKey: String
    ) : Call<ResponseTvList>

    @GET("tv/{id}")
    fun getTvShowDetail(
        @Path("id") id: Int,
        @Query("api_key") apiKey: String
    ) : Call<ResponseTv>
}