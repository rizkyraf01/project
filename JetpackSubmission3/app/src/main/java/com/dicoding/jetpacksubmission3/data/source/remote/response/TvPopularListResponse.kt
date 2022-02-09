package com.dicoding.jetpacksubmission3.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class TvPopularListResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<TvPopularResponse>,
    @SerializedName("total_results")
    val totalResults: Int,
    @SerializedName("total_pages")
    val totalPages: Int
)
