package com.example.mostpopular.popular.data.model

import com.google.gson.annotations.SerializedName


data class Result(

    @SerializedName("uri")
    val uri: String? = null,

    @SerializedName("url")
    val url: String? = null,

    @SerializedName("id")
    val id: Long? = null,

    @SerializedName("asset_id")
    val assetId: Long? = null,

    @SerializedName("source")
    val source: String? = null,

    @SerializedName("published_date")
    val publishedDate: String? = null,

    @SerializedName("updated")
    val updated: String? = null,

    @SerializedName("section")
    val section: String? = null,

    @SerializedName("subsection")
    val subsection: String? = null,

    @SerializedName("nytdsection")
    val nytdsection: String? = null,

    @SerializedName("adx_keywords")
    val adxKeywords: String? = null,

    @SerializedName("column")
    val column: Any? = null,

    @SerializedName("byline")
    val byline: String? = null,

    @SerializedName("type")
    val type: String? = null,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("abstract")
    val _abstract: String? = null,

    @SerializedName("des_facet")
    val desFacet: List<String>? = null,

    @SerializedName("org_facet")
    val orgFacet: List<Any>? = null,

    @SerializedName("per_facet")
    val perFacet: List<Any>? = null,

    @SerializedName("geo_facet")
    val geoFacet: List<Any>? = null,

    @SerializedName("media")
    val media: List<Medium>? = null,

    @SerializedName("eta_id")
    val etaId: Int? = null,

    var numberOfDay: Int? = 0
)