package com.dicoding.jetpacksubmission3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvPopularResponse (
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("episode_run_time")
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,
    @SerializedName("homepage")
    val homepage: String,
    )