package com.example.mostpopular.popular.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


data class PopularArticalResponse(

    @SerializedName("status")
    val status: String? = null,

    @SerializedName("copyright")
    val copyright: String? = null,

    @SerializedName("num_results")
    val numResults: Int? = null,

    @SerializedName("results")
    val results: List<Result>? = null

)