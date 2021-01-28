package com.carintis.android.quotesapp.domain

import com.google.gson.annotations.SerializedName

data class Quote(
    @SerializedName("key") val key: Long = 0,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val title: String,
    @SerializedName("media") val media: String,
    @SerializedName("author") val author: String,
    @SerializedName("cat") val cat: String,
    val isFavourite: Boolean = false
)