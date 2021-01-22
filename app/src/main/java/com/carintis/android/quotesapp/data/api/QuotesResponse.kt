package com.carintis.android.quotesapp.data.api

import com.carintis.android.quotesapp.domain.Quote
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

//data class QuotesResponse(@field:Json(name = "message") val messages: List<Quote> = emptyList()) {
//
//  fun toDomainEntity(): List<Quote> = messages.map { Quote(media = it., isFavourite = false) }
//}

data class QuotesResponse(
  @SerializedName("docs") val results: List<Quote>
)