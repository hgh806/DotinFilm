package com.borna.dotinfilm.sections.presentation.detail.components

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.leanback.widget.Action
import androidx.leanback.widget.Presenter
import com.borna.dotinfilm.R

class FilmActionButtonPresenter : Presenter() {
    private var itemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_two_buttons, parent, false)
        return ViewHolder(view)
    }

    fun setOnItemClickListener(listener: () -> Unit) {
        this.itemClickListener = listener
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val playButton = viewHolder.view.findViewById<Button>(R.id.play_button)
        val likeButton = viewHolder.view.findViewById<Button>(R.id.like_button)

        playButton.setOnClickListener {
            // Handle Button 1 click
        }

        likeButton.setOnClickListener {
            itemClickListener?.invoke()
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        // Clean up resources, if necessary
    }
}