package com.borna.dotinfilm.sections.presentation.detail

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
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
import com.borna.dotinfilm.sections.presentation.detail.components.ActionButtonType
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

        onItemViewSelectedListener = ItemViewSelectedListener()
        onItemViewClickedListener = ItemViewClickListener()
    }

    private fun setupLikeAndPlayButtons(isLiked: Boolean) {
        val filmActionButtonPresenter = FilmActionButtonPresenter()
        filmActionButtonPresenter.setOnItemClickListener {
            likeClickListener?.invoke()
            Handler(Looper.getMainLooper()).postDelayed({rootAdapter.notifyItemRangeChanged(0,1)}, 1000)
        }
        val buttonsObjectAdapter = ArrayObjectAdapter(filmActionButtonPresenter)
        buttonsObjectAdapter.add(ActionButtonType.Play)
        buttonsObjectAdapter.add(ActionButtonType.Like(isLiked)).apply {
            Log.i("TAG", "setupLikeAndPlayButtons: $isLiked")
        }
        val listRow = ListRow(buttonsObjectAdapter)
        //prevent to add more than one row
        if (rootAdapter.size() > 0)
            rootAdapter.remove(rootAdapter.get(0)!!)
        rootAdapter.add(0, listRow)
    }

    fun bindData(sections: List<String>, isLiked: Boolean) {
        setupLikeAndPlayButtons(isLiked)

        //prevent to add more than one row
        if (rootAdapter.size() > 1)
            return

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