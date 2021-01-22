package com.carintis.android.quotesapp.presentation.quotes

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
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.TransitionInflater
import com.carintis.android.quotesapp.R
import com.carintis.android.quotesapp.presentation.QuotesAdapter
import kotlinx.android.synthetic.main.fragment_quote_list.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
class QuoteListFragment : Fragment() {

  private val viewModel: QuoteListViewModel by viewModels { QuoteListViewModelFactory }
  private var isLoadingMoreItems = false

  override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
  ): View? {
    val view = inflater.inflate(R.layout.fragment_quote_list, container, false)
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

  private fun createAdapter(): QuotesAdapter {
    return QuotesAdapter { view, quote ->
      val extraInfoForSharedElement = FragmentNavigatorExtras(
          view to quote.media
      )
      val toQuoteFragment =
          QuoteListFragmentDirections.toQuoteFragment(quote.id, quote.media, quote.isFavourite)

      navigate(toQuoteFragment, extraInfoForSharedElement)
    }
  }

  private fun setupRecyclerView(view: View, quotesAdapter: QuotesAdapter) {
    view.recycler_view_quotes.run {
      adapter = quotesAdapter

      setHasFixedSize(true)
      addOnScrollListener(createInfiniteScrollListener(layoutManager as GridLayoutManager))

      postponeEnterTransition()
      viewTreeObserver.addOnPreDrawListener {
        startPostponedEnterTransition()
        true
      }
    }
  }

  private fun createInfiniteScrollListener(
      gridLayoutManager: GridLayoutManager
  ): RecyclerView.OnScrollListener {
    return object : InfiniteScrollListener(gridLayoutManager, QuoteListViewModel.PAGE_SIZE) {
      override fun loadMoreItems() {
        isLoadingMoreItems = true
        viewModel.getMoreQuotes(QuoteListViewModel.PAGE_SIZE)
      }

      override fun isLoading(): Boolean = isLoadingMoreItems
    }
  }

  private fun observeViewModel(quotesAdapter: QuotesAdapter) {
    viewModel.quoteList.observe(viewLifecycleOwner) {
      quotesAdapter.submitList(it)
      isLoadingMoreItems = false
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