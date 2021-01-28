package com.carintis.android.quotesapp.data.cache

import com.carintis.android.quotesapp.data.api.Categories
import com.carintis.android.quotesapp.domain.Quote
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

  override fun doesQuoteExist(quote: CachedQuote): Boolean {
    return quotesDao.isQuoteExist(quote.id)
  }


  override fun insertQuote(quote: CachedQuote)
  {
    return quotesDao.insertQuote(quote)
  }

  override fun getFilteredQuotes(category: String) : Flow<List<CachedQuote>> {
    return quotesDao.filteredQuotes(category)
  }


}