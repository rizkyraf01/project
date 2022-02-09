package com.dicoding.jetpacksubmission3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MoviesListResponse (
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<MoviesResponse>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
    )