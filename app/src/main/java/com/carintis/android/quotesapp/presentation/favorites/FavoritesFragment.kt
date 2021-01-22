package com.carintis.android.quotesapp.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.carintis.android.quotesapp.R
import com.carintis.android.quotesapp.presentation.QuotesAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_favorites.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class FavoritesFragment : Fragment() {
  private val viewModel: FavoritesViewModel by viewModels { FavoritesViewModelFactory }

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_favorites, container, false)
    val quotesAdapter = QuotesAdapter()

    setupRecyclerView(view, quotesAdapter)
    observeViewModel(quotesAdapter)

    return view
  }

  private fun setupRecyclerView(view: View, quotesAdapter: QuotesAdapter) {
    view.recycler_view_favorite_quotes.run {
      adapter = quotesAdapter
      setHasFixedSize(true)
    }
  }

  private fun observeViewModel(quotesAdapter: QuotesAdapter) {
    viewModel.favoriteQuotes.observe(viewLifecycleOwner) {
      quotesAdapter.submitList(it)

      if (it.isEmpty()) {
        text_view_no_favorites.visibility = View.VISIBLE
      } else {
        text_view_no_favorites.visibility = View.INVISIBLE
      }
    }
  }
}