package com.carintis.android.quotesapp.data

import com.carintis.android.quotesapp.data.api.Api
import com.carintis.android.quotesapp.data.api.Categories
import com.carintis.android.quotesapp.data.cache.Cache
import com.carintis.android.quotesapp.data.cache.CachedQuote
import com.carintis.android.quotesapp.domain.Quote
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class QuotesRepository(private val api: Api, private val cache: Cache) : QuoteRepository {

    private var _category: String = Categories.All.toString()

    override fun getCachedQuotes(): Flow<List<Quote>> {
        return if (_category == Categories.All.toString())
            cache.getAllQuotes()
                .map { quoteList ->
                    quoteList.map { it.toDomainEntity() }
                }
        else
            cache.getFilteredQuotes("$_category%").map { quoteList ->
                quoteList.map { it.toDomainEntity() }
            }
    }

    override fun getCachedFavoriteQuotes(): Flow<List<Quote>> {
        return cache.getFavoriteQuotes()
            .map { favoriteQuotes ->
                favoriteQuotes.map { it.toDomainEntity() }
            }
    }

    override suspend fun getApiQuote(numberOfImages: Int): Quote {
        return api.getQuote(1).first()
    }

    override suspend fun getApiQuotes(numberOfImages: Int): List<Quote> {
        return api.getQuotes(numberOfImages)
    }

    override suspend fun updateCachedQuotes(quotes: List<Quote>) {

        for (quote in quotes.map {
            CachedQuote(
                it.key,
                it.id,
                it.title,
                it.media,
                it.author,
                it.cat,
                it.isFavourite
            )
        }) {
            if (!cache.doesQuoteExist(quote))
                cache.insertQuote(quote)
        }
    }

    override suspend fun updateQuoteFavoriteStatus(quoteId: Long, isFavorite: Boolean) {
        cache.updateQuoteFavoriteStatus(quoteId, isFavorite)
    }

    override suspend fun setCategoryFilter(category: String) {
        _category = category
    }
}