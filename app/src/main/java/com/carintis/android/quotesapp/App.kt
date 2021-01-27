package com.carintis.android.quotesapp

import android.app.Application
import androidx.room.Room
import com.carintis.android.quotesapp.data.QuotesRepository
import com.carintis.android.quotesapp.data.api.Api
import com.carintis.android.quotesapp.data.api.ConnectionManager
import com.carintis.android.quotesapp.data.cache.QuotesDatabase
import com.carintis.android.quotesapp.data.cache.RoomCache
import com.carintis.android.quotesapp.domain.QuoteRepository
import com.carintis.android.quotesapp.presentation.quotedetail.QuoteViewModelFactory
import com.carintis.android.quotesapp.presentation.quotes.QuoteListViewModelFactory
import com.carintis.android.quotesapp.presentation.favorites.FavoritesViewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class App : Application() {

  override fun onCreate() {
    super.onCreate()

    val repository = createRepository()
    ConnectionManager.setContext(this)

    QuoteListViewModelFactory.inject(repository)
    QuoteViewModelFactory.inject(repository)
    FavoritesViewModelFactory.inject(repository)
  }

  private fun createRepository(): QuoteRepository {
    val quotesApi = Api.create()
    val database = createDatabase()
    val quotesDao = database.quotesDao()
    val quotesCache = RoomCache(quotesDao)

    return QuotesRepository(quotesApi, quotesCache)
  }

  private fun createDatabase(): QuotesDatabase =
      Room.databaseBuilder(this, QuotesDatabase::class.java, "quotes.db")
        .fallbackToDestructiveMigration()
          .build()
}