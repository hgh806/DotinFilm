package com.borna.dotinfilm.sections.presentation.film

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.RowHeaderPresenter
import com.borna.dotinfilm.R

class CustomHeaderPresenter : RowHeaderPresenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        // Inflate your custom header layout
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: Presenter.ViewHolder, item: Any?) {
        val headerItem = item as ListRow
        val headerTitle = viewHolder.view.findViewById<TextView>(R.id.header_title)
        headerTitle.text = headerItem.headerItem.name
    }

    override fun onUnbindViewHolder(viewHolder: Presenter.ViewHolder) {
        // Clean up resources if needed
    }
}