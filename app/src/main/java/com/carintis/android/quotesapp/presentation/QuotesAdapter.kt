package com.carintis.android.quotesapp.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.carintis.android.quotesapp.R
import com.carintis.android.quotesapp.domain.Quote
import com.carintis.android.quotesapp.presentation.extensions.load
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_view_quote_item.*

class QuotesAdapter(
    private val onQuoteClickListener: ((view: View, quote: Quote) -> Unit)? = null
) : ListAdapter<Quote, QuotesAdapter.QuoteViewHolder>(ITEM_COMPARATOR) {

    init {
        setHasStableIds(true);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val holder = QuoteViewHolder(
            inflater.inflate(
                R.layout.recycler_view_quote_item,
                parent,
                false
            )
        )
        holder.setIsRecyclable(false)
        return holder
    }

    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        val item: Quote = getItem(position)
        holder.bind(item, onQuoteClickListener)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        LayoutContainer {

        override val containerView: View?
            get() = itemView

        fun bind(
            item: Quote,
            onQuoteClickListener: ((view: View, image: Quote) -> Unit)?
        ) {
            with(image_view_quotes) {
                load(item.media) {
                    onQuoteClickListener?.let {
                        it(this, item)
                    }
                }
                transitionName = item.media
            }
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Quote>() {
    override fun areItemsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem.key == newItem.key
    }

    override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
        return oldItem == newItem
    }
}