package com.dicoding.jetpacksubmission3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesTrendResponse (
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("genres")
    val genres: List<GenreResponse>,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val originalTitle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("runtime")
    val runtime: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("homepage")
    val homepage: String,
    @SerializedName("vote_average")
    val voteAverage: Double
)