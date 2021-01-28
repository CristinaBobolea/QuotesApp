package com.carintis.android.quotesapp.presentation.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Suppress("UNCHECKED_CAST")
object FavoritesViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repository: QuoteRepository

    fun inject(repository: QuoteRepository) {
        FavoritesViewModelFactory.repository = repository
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FavoritesViewModel(repository) as T
    }
}