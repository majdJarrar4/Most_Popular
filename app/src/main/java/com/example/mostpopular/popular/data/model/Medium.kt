package com.example.mostpopular.popular.data.model

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName


class Medium(

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("subtype")
    val subtype: String? = null,

    @SerializedName("caption")
    val caption: String? = null,

    @SerializedName("copyright")
    val copyright: String? = null,

    @SerializedName("approved_for_syndication")
    val approvedForSyndication: Int? = null,

    @SerializedName("media-metadata")
    val mediaMetadata: List<MediaMetadatum>? = null

)