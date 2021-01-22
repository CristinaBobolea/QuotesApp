package com.carintis.android.quotesapp.data.cache

import kotlinx.coroutines.flow.Flow

interface Cache {

  fun getAllQuotes(): Flow<List<CachedQuote>>
  fun getFavoriteQuotes(): Flow<List<CachedQuote>>
  fun updateCachedQuotes(quotes: List<CachedQuote>)
  fun updateQuoteFavoriteStatus(quoteId: Long, isFavorite: Boolean)
}