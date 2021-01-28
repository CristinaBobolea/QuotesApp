package com.carintis.android.quotesapp.presentation.quotedetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carintis.android.quotesapp.domain.QuoteRepository

@Suppress("UNCHECKED_CAST")
object QuoteViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repository: QuoteRepository

    fun inject(repository: QuoteRepository) {
        QuoteViewModelFactory.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return QuoteViewModel(repository) as T
    }
}