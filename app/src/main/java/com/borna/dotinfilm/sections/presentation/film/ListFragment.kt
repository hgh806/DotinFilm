package com.borna.dotinfilm.sections.presentation.film

import android.os.Bundle
import android.view.View
import androidx.leanback.app.RowsSupportFragment
import androidx.leanback.widget.*
import com.borna.dotinfilm.sections.domain.models.Film
import com.borna.dotinfilm.sections.domain.models.Section


class ListFragment : RowsSupportFragment() {

    private var itemSelectedListener: ((Film) -> Unit)? = null
    private var itemClickListener: ((Film) -> Unit)? = null

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
        sections.forEach { section ->
            val arrayObjectAdapter = ArrayObjectAdapter(ItemPresenter(cardType = section.cardType))

            section.films.forEach {
                arrayObjectAdapter.add(it)
            }

            val headerItem = HeaderItem(section.name)
            val listRow = ListRow(headerItem, arrayObjectAdapter)
            rootAdapter.add(listRow)
        }
    }


    fun setOnContentSelectedListener(listener: (Film) -> Unit) {
        this.itemSelectedListener = listener
    }

    fun setOnItemClickListener(listener: (Film) -> Unit) {
        this.itemClickListener = listener
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