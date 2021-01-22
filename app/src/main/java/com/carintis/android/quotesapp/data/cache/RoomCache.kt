package com.carintis.android.quotesapp.data.cache

import kotlinx.coroutines.flow.Flow

class RoomCache(private val quotesDao: QuotesDao) : Cache {

  override fun getAllQuotes(): Flow<List<CachedQuote>> {
    return quotesDao.getAllQuotes()
  }

  override fun getFavoriteQuotes(): Flow<List<CachedQuote>> {
    return quotesDao.getFavoriteQuotes()
  }

  override fun updateCachedQuotes(quotes: List<CachedQuote>) {
    return quotesDao.insert(quotes)
  }

  override fun updateQuoteFavoriteStatus(quoteId: Long, isFavorite: Boolean) {
    quotesDao.update(quoteId, isFavorite)
  }
}