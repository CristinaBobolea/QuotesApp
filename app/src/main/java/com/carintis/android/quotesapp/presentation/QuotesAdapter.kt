/*
 * Copyright (c) 2020 Razeware LLC
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * Notwithstanding the foregoing, you may not use, copy, modify, merge, publish,
 * distribute, sublicense, create a derivative work, and/or sell copies of the
 * Software in any work that is designed, intended, or marketed for pedagogical or
 * instructional purposes related to programming, coding, application development,
 * or information technology.  Permission for such use, copying, modification,
 * merger, publication, distribution, sublicensing, creation of derivative works,
 * or sale is expressly withheld.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
    val inflater = LayoutInflater.from(parent.context)

    return QuoteViewHolder(
        inflater.inflate(
            R.layout.recycler_view_quote_item,
            parent,
            false
        )
    )
  }

  override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
    val item: Quote = getItem(position)
    holder.bind(item, onQuoteClickListener)
  }

  // Need to implement LayoutContainer so that views are cached correctly
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
    return oldItem.media == newItem.media
  }

  override fun areContentsTheSame(oldItem: Quote, newItem: Quote): Boolean {
    return oldItem == newItem
  }
}