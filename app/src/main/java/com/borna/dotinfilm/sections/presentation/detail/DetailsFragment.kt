package com.borna.dotinfilm.sections.presentation.detail

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.FocusHighlight
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import com.borna.dotinfilm.R
import com.borna.dotinfilm.sections.domain.models.Film
import com.borna.dotinfilm.sections.presentation.detail.components.FilmActionButtonPresenter
import com.borna.dotinfilm.sections.presentation.film.CustomHeaderPresenter


class DetailsFragment : RowsSupportFragment() {

    private var itemSelectedListener: ((Film) -> Unit)? = null
    private var itemClickListener: ((Film) -> Unit)? = null
    private var likeClickListener: (() -> Unit)? = null

    private val listRowPresenter = object : ListRowPresenter(FocusHighlight.ZOOM_FACTOR_MEDIUM) {
        override fun isUsingDefaultListSelectEffect(): Boolean {
            return false
        }
    }.apply {
        shadowEnabled = false
        headerPresenter = CustomHeaderPresenter()
    }

    private var rootAdapter: ArrayObjectAdapter = ArrayObjectAdapter(listRowPresenter)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = rootAdapter

        setupLikeAndPlayButtons()

        onItemViewSelectedListener = ItemViewSelectedListener()
        onItemViewClickedListener = ItemViewClickListener()
    }

    private fun setupLikeAndPlayButtons() {
        val filmActionButtonPresenter = FilmActionButtonPresenter()
        filmActionButtonPresenter.setOnItemClickListener {
            likeClickListener?.invoke()
        }
        val buttonsObjectAdapter = ArrayObjectAdapter(filmActionButtonPresenter)
        buttonsObjectAdapter.add("play")
        buttonsObjectAdapter.add("like")
        val listRow = ListRow(buttonsObjectAdapter)
        rootAdapter.add(listRow)
    }

    fun bindData(sections: List<String>) {
        val arrayObjectAdapter = ArrayObjectAdapter(GalleryPresenter())
        sections.forEach {
            arrayObjectAdapter.add(it)
        }
        val headerItem = HeaderItem(getString(R.string.film_images))
        val listRow = ListRow( headerItem, arrayObjectAdapter)
        rootAdapter.add(listRow)
    }

    fun setOnLikeClickListener(listener: () -> Unit) {
        this.likeClickListener = listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is Film) {
                itemSelectedListener?.invoke(item)
            }
        }
    }

    inner class ItemViewClickListener : OnItemViewClickedListener {
        override fun onItemClicked(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?
        ) {
            if (item is Film) {
                itemClickListener?.invoke(item)
            }
        }
    }
}