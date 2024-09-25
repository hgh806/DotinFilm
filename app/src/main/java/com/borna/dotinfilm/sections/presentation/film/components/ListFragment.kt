package com.borna.dotinfilm.sections.presentation.film.components

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
import com.borna.dotinfilm.sections.domain.models.Film
import com.borna.dotinfilm.sections.domain.models.Section


class ListFragment : RowsSupportFragment() {

    private var itemSelectedListener: ((Film) -> Unit)? = null
    private var itemClickListener: ((Film) -> Unit)? = null
    private var itemLikeChangeListener: ((Film) -> Unit)? = null

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

    fun bindData(sections: List<Section>) {
        sections.forEachIndexed { index, section ->
            val arrayObjectAdapter = ArrayObjectAdapter(
                ItemPresenter(cardType = section.cardType) {
                    itemLikeChangeListener?.invoke(it)
                }
            )

            section.films.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(section.name)
            val listRow = ListRow(headerItem, arrayObjectAdapter)
            if (rootAdapter.size() > index)
                rootAdapter.replace(index, listRow)
            else
                rootAdapter.add(listRow)
        }
    }


    fun setOnContentSelectedListener(listener: (Film) -> Unit) {
        this.itemSelectedListener = listener
    }

    fun setOnItemClickListener(listener: (Film) -> Unit) {
        this.itemClickListener = listener
    }

    fun setOnLikeChangeListener(listener: (Film) -> Unit) {
        itemLikeChangeListener = listener
    }

    inner class ItemViewSelectedListener : OnItemViewSelectedListener {
        override fun onItemSelected(
            itemViewHolder: Presenter.ViewHolder?,
            item: Any?,
            rowViewHolder: RowPresenter.ViewHolder?,
            row: Row?,
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
            row: Row?,
        ) {
            if (item is Film) {
                itemClickListener?.invoke(item)
            }
        }
    }
}