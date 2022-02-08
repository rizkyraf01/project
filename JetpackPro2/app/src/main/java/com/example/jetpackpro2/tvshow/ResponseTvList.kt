package com.example.jetpackpro2.tvshow

import com.google.gson.annotations.SerializedName

data class ResponseTvList (
        @SerializedName("results")
        val results: List<ResponseTv>,
)