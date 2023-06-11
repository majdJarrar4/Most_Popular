package com.example.mostpopular.popular.data.model

import com.google.gson.annotations.SerializedName


class MediaMetadatum(

    @SerializedName("url") val
    url: String? = null,

    @SerializedName("format")
    val format: String? = null,

    @SerializedName("height")
    val height: Int? = null,

    @SerializedName("width")
    val width: Int? = null

)