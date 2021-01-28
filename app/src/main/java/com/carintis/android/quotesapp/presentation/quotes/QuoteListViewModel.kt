package com.carintis.android.quotesapp.presentation.quotes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carintis.android.quotesapp.data.api.ConnectionManager
import com.carintis.android.quotesapp.domain.Quote
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class QuoteListViewModel(
    private val repository: QuoteRepository
) : ViewModel() {

    companion object {
        const val PAGE_SIZE = 10
    }

    val quoteList: LiveData<List<Quote>>
        get() = _quoteList

    private val _quoteList: MutableLiveData<List<Quote>> = MutableLiveData()

    init {
        observeCacheUpdates()
    }

    fun getMoreQuotes(numberOfQuotes: Int) {
        updateCacheWithQuotesFromApi(numberOfQuotes)
    }

    private fun updateCacheWithQuotesFromApi(numberOfQuotes: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val quotes = repository.getApiQuotes(numberOfQuotes)
            repository.updateCachedQuotes(quotes)
        }
    }

    private fun observeCacheUpdates() {
        viewModelScope.launch {
            repository.getCachedQuotes()
                .onEach {
                    if (it.isEmpty() && ConnectionManager.isConnected()) {
                        getMoreQuotes(PAGE_SIZE)
                    }
                }
                .flowOn(Dispatchers.IO)
                .collect { handleQuotes(it) }
        }
    }


    private fun handleQuotes(quotes: List<Quote>) {
        _quoteList.value = quotes
    }
}