package com.carintis.android.quotesapp.presentation.about

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carintis.android.quotesapp.domain.QuoteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilterDialogViewModel(private val repository: QuoteRepository) : ViewModel() {

    fun updateFilters(category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.setCategoryFilter(category)
        }
    }


}