package com.carintis.android.quotesapp.presentation.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carintis.android.quotesapp.domain.QuoteRepository

@Suppress("UNCHECKED_CAST")
object FilterDialogViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repository: QuoteRepository

    fun inject(repository: QuoteRepository) {
        FilterDialogViewModelFactory.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FilterDialogViewModel(repository) as T
    }
}