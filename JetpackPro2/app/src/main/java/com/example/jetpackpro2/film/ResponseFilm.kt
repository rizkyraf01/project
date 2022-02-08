package com.example.jetpackpro2.film

import com.google.gson.annotations.SerializedName

data class ResponseFilm (
    @SerializedName("backdrop_path")
    val background: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_title")
    val orititle: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val poster: String,
    @SerializedName("vote_average")
    val ratee: Double,
    @SerializedName("title")
    val title: String,
)