package com.carintis.android.quotesapp.presentation.quotes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@Suppress("UNCHECKED_CAST")
object QuoteListViewModelFactory : ViewModelProvider.Factory {

  private lateinit var repository: QuoteRepository

  fun inject(repository: QuoteRepository) {
    QuoteListViewModelFactory.repository = repository
  }

  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    return QuoteListViewModel(repository) as T
  }
}