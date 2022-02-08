package com.example.jetpackpro2.utils

import com.example.jetpackpro2.datasource.DataFilmEntity
import com.example.jetpackpro2.datasource.DataTvEntity
import com.example.jetpackpro2.film.ResponseFilm
import com.example.jetpackpro2.tvshow.ResponseTv

object DataDummy {


    fun generateRemoteMovies(): List<ResponseFilm>{
        val movies = ArrayList<ResponseFilm>()
        movies.add(ResponseFilm(
                "back",
                1,
                "Title",
                " overview",
                "poster",7.5,
                "title"

        ))
        movies.add(ResponseFilm(
                "back",
                1,
                "Title",
                " overview",
                "poster",7.5,
                "title"
        ))
        movies.add(ResponseFilm(
                "back",
                1,
                "Title",
                " overview",
                "poster",7.5,
                "title"
        ))
        movies.add(ResponseFilm(
                "back",
                1,
                "Title",
                " overview",
                "poster",7.5,
                "title"
        ))
        movies.add(ResponseFilm(
                "back",
                1,
                "Title",
                " overview",
                "poster",7.5,
                "title"
        ))
        return movies
    }

    fun generateRemoteDummyMovies(): ResponseFilm{
        return ResponseFilm(
                "back",
                1,
                "Title",
                " overview",
                "poster",7.5,
                "title")
    }

    fun generateRemoteTvShow(): List<ResponseTv>{
        val tvShow = ArrayList<ResponseTv>()
        tvShow.add(
                ResponseTv(
                        "Back",
                      1,
                        "Name",
                        "Over",
                        "poster",
                        7.2
                ))
        tvShow.add(
                ResponseTv(
                        "Back",
                        1,
                        "Name",
                        "Over",
                        "poster",
                        7.2
                ))
        tvShow.add(
                ResponseTv(
                        "Back",
                        1,
                        "Name",
                        "Over",
                        "poster",
                        7.2
                ))
        tvShow.add(
                ResponseTv(
                        "Back",
                        1,
                        "Name",
                        "Over",
                        "poster",
                        7.2
                ))
        tvShow.add(
                ResponseTv(
                        "Back",
                        1,
                        "Name",
                        "Over",
                        "poster",
                        7.2
                ))
        return tvShow
    }

    fun generateRemoteDummyTvShow(): ResponseTv{
        return ResponseTv(
                "Back",
                1,
                "Name",
                "Over",
                "poster",
                7.2
        )
    }

    fun generateMovies(): List<DataFilmEntity>{
        val movies = ArrayList<DataFilmEntity>()
        movies.add(
                DataFilmEntity(1,
                        "title",
                        "poster",
                        7.2,
                        "over",
                        "banner"
                )
        )
        movies.add(
                DataFilmEntity(2,
                        "title",
                        "poster",
                        7.2,
                        "over",
                        "banner"
                )
        )
        movies.add(
                DataFilmEntity(3,
                        "title",
                        "poster",
                        7.2,
                        "over",
                        "banner"
                )
        )
        movies.add(
                DataFilmEntity(4,
                        "title",
                        "poster",
                        7.2,
                        "over",
                        "banner"
                )
        )
        movies.add(
                DataFilmEntity(5,
                        "title",
                        "poster",
                        7.2,
                        "over",
                        "banner"
                )
        )
        return movies
    }

    fun generateDetailMovies(): DataFilmEntity{
        return DataFilmEntity(1,
                "title",
                "poster",
                7.2,
                "over",
                "banner",
        false
        )
    }



    fun generateTvShow(): List<DataTvEntity>{
        val tvShow = ArrayList<DataTvEntity>()
        tvShow.add(
                DataTvEntity(1,
                        "title",
                        7.2,
                        "poster",
                        "over",
                        "banner"
                )
        )
        tvShow.add(
                DataTvEntity(2,
                        "title",
                        7.2,
                        "poster",
                        "over",
                        "banner"
                )
        )
        tvShow.add(
                DataTvEntity(3,
                        "title",
                        7.2,
                        "poster",
                        "over",
                        "banner"
                )
        )
        tvShow.add(
                DataTvEntity(4,
                        "title",
                        7.2,
                        "poster",
                        "over",
                        "banner"
                )
        )
        tvShow.add(
                DataTvEntity(5,
                        "title",
                        7.2,
                        "poster",
                        "over",
                        "banner"
                )
        )
        return tvShow
    }

    fun generateDetailTvShow(): DataTvEntity{
        return DataTvEntity(1,
                "title",
                7.2,
                "poster",
                "over",
                "banner",
                false
        )
    }
    }