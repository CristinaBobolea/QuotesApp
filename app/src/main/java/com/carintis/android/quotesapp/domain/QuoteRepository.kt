package com.carintis.android.quotesapp.domain

import com.carintis.android.quotesapp.data.api.Categories
import kotlinx.coroutines.flow.Flow

interface QuoteRepository {

  fun getCachedQuotes(): Flow<List<Quote>>
  fun getCachedFavoriteQuotes(): Flow<List<Quote>>
  suspend fun getApiQuotes(numberOfImages: Int = 10): List<Quote>
  suspend fun getApiQuote(numberOfImages: Int = 1): Quote
  suspend fun updateCachedQuotes(quotes: List<Quote>)
  suspend fun updateQuoteFavoriteStatus(quoteId: Long, isFavorite: Boolean)
  suspend fun setCategoryFilter(category: String)
}