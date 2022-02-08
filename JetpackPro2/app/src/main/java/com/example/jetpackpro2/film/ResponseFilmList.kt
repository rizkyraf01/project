package com.example.jetpackpro2.film

import com.google.gson.annotations.SerializedName

class ResponseFilmList (
        @SerializedName("page")
        val page: Int,
    @SerializedName("results")
    val results: List<ResponseFilm>,
        @SerializedName("total_results")
        val totalResults: Int,
        @SerializedName("total_pages")
        val totalPages: Int
)