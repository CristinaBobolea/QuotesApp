package com.carintis.android.quotesapp.presentation.quotedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val repository: QuoteRepository) : ViewModel() {

    fun updateQuoteFavoriteStatus(quoteId: Long, isFavorite: Boolean) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateQuoteFavoriteStatus(quoteId, isFavorite)
        }
    }
}