package com.carintis.quotesapp.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Author(
    @SerializedName("name") val name: String,
    @SerializedName("url") val url: String
) : Serializable
