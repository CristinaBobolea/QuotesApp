package com.carintis.android.quotesapp.presentation.about

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavDirections
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.carintis.android.quotesapp.R
import com.carintis.android.quotesapp.data.api.Categories
import com.carintis.android.quotesapp.presentation.QuotesAdapter
import com.carintis.android.quotesapp.presentation.quotedetail.QuoteViewModel
import com.carintis.android.quotesapp.presentation.quotedetail.QuoteViewModelFactory
import com.carintis.android.quotesapp.presentation.quotes.QuoteListFragmentDirections
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_favorites.*
import kotlinx.android.synthetic.main.fragment_filter.*

class FilterFragment : BottomSheetDialogFragment() {

    private val viewModel: FilterDialogViewModel by viewModels { FilterDialogViewModelFactory }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_filter, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initCategoriesSpinner()
        doneButton.setOnClickListener()
        {
            val extraInfoForSharedElement = FragmentNavigatorExtras()
            val toQuoteListFragment =
                FilterFragmentDirections.toDiscoverList(true)

            navigate(toQuoteListFragment, extraInfoForSharedElement)
            dismiss()
        }
        cancelButton.setOnClickListener {
            dismiss()
        }
    }
    private fun initCategoriesSpinner() {
        val adapter = ArrayAdapter<String>(
            spnCategories.context,
            R.layout.filter_category_item,
            Categories.values().map {
                it.name
            }
        )
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1)

        spnCategories.adapter = adapter

        spnCategories.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                viewModel.updateFilters(spnCategories.adapter.getItem(p2) as String)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
        }
    }

    private fun navigate(destination: NavDirections, extraInfo: FragmentNavigator.Extras) = with(findNavController()) {
        currentDestination?.getAction(destination.actionId)
            ?.let { navigate(destination, extraInfo) }
    }
}
