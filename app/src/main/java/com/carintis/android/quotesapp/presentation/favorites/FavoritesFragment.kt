package com.carintis.android.quotesapp.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.carintis.android.quotesapp.R
import com.carintis.android.quotesapp.presentation.QuotesAdapter
import com.carintis.android.quotesapp.presentation.quotes.QuoteListFragmentDirections
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

    val quotesAdapter = createAdapter()

    setupRecyclerView(view, quotesAdapter)
    observeViewModel(quotesAdapter)

    return view
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    setExitToFullScreenTransition()
    setReturnFromFullScreenTransition()
  }

  private fun setupRecyclerView(view: View, quotesAdapter: QuotesAdapter) {
    view.recycler_view_favorite_quotes.run {
      adapter = quotesAdapter
      setHasFixedSize(true)
    }
  }

  private fun createAdapter(): QuotesAdapter {
    return QuotesAdapter { view, quote ->
      val extraInfoForSharedElement = FragmentNavigatorExtras(
        view to quote.media
      )
      val toQuoteFragment =
        QuoteListFragmentDirections.toQuoteFragment(quote.key, quote.media, quote.isFavourite)

      navigate(toQuoteFragment, extraInfoForSharedElement)
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

  private fun setExitToFullScreenTransition() {
    exitTransition =
      TransitionInflater.from(context).inflateTransition(R.transition.quote_list_exit_transition)
  }

  private fun setReturnFromFullScreenTransition() {
    reenterTransition =
      TransitionInflater.from(context).inflateTransition(R.transition.quote_list_return_transition)
  }

  private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) = with(findNavController()) {
    currentDestination?.getAction(destination.actionId)
      ?.let { navigate(destination, extraInfo) }
  }
}