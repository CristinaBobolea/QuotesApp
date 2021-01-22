package com.carintis.android.quotesapp.presentation.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carintis.android.quotesapp.domain.Quote
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FavoritesViewModel(private val repository: QuoteRepository) : ViewModel() {

  val favoriteQuotes: LiveData<List<Quote>>
      get() = _favoriteQuotes

  private val _favoriteQuotes: MutableLiveData<List<Quote>> = MutableLiveData()

  init {
    observeCacheUpdates()
  }

  private fun observeCacheUpdates() {
    viewModelScope.launch {
      repository.getCachedFavoriteQuotes()
          .flowOn(Dispatchers.IO)
          .collect { handleQuotes(it) }
    }
  }

  private fun handleQuotes(quotes: List<Quote>) {
    _favoriteQuotes.value = quotes
  }
}