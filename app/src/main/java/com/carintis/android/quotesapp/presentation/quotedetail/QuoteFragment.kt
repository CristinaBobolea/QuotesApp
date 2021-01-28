package com.carintis.android.quotesapp.presentation.quotedetail

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.onNavDestinationSelected
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.carintis.android.quotesapp.R
import kotlinx.android.synthetic.main.fragment_quote.*

class QuoteFragment : Fragment() {

    private val args: QuoteFragmentArgs by navArgs()
    private val viewModel: QuoteViewModel by viewModels { QuoteViewModelFactory }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return inflater.inflate(R.layout.fragment_quote, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val (key, picture, isFavorite) = args

        setSharedElementTransitionOnEnter()
        postponeEnterTransition()
        setupFavoriteButton(key, isFavorite)

        image_view_full_screen_quote.apply {
            transitionName = picture
            startEnterTransitionAfterLoadingImage(picture, this)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return item.onNavDestinationSelected(findNavController()) || super.onOptionsItemSelected(
            item
        )
    }

    private fun setupFavoriteButton(key: Long, quoteIsFavorite: Boolean) {
        updateButtonBackground(quoteIsFavorite)
        button_favorite.isChecked = quoteIsFavorite
        button_favorite.setOnCheckedChangeListener { _, isChecked ->
            viewModel.updateQuoteFavoriteStatus(key, isChecked)
            updateButtonBackground(isChecked)
        }
    }

    private fun updateButtonBackground(pictureIsFavorite: Boolean) {
        val buttonImageResource: Int = if (pictureIsFavorite) {
            R.drawable.ic_star_full_42dp
        } else {
            R.drawable.ic_star_border_42dp
        }

        button_favorite.background = resources.getDrawable(buttonImageResource, null)
    }

    private fun setSharedElementTransitionOnEnter() {
        sharedElementEnterTransition = TransitionInflater.from(context)
            .inflateTransition(R.transition.shared_element_transition)
    }

    private fun startEnterTransitionAfterLoadingImage(imageAddress: String, imageView: ImageView) {
        Glide.with(this)
            .load(imageAddress)
            .dontAnimate()
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable,
                    model: Any,
                    target: Target<Drawable>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    return false
                }
            })
            .into(imageView)
    }
}