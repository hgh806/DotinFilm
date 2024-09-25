package com.borna.dotinfilm.sections.presentation.detail.components

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatImageButton
import androidx.leanback.widget.Presenter
import com.borna.dotinfilm.R

class FilmActionButtonPresenter : Presenter() {
    private var itemClickListener: (() -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val layoutId = R.layout.play_like_button
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    fun setOnItemClickListener(listener: () -> Unit) {
        this.itemClickListener = listener
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val playButton = viewHolder.view.findViewById<Button>(R.id.play_button)
        val likeButton = viewHolder.view.findViewById<AppCompatImageButton>(R.id.like_button)

        val key = item as String
        if (key == "like") {
            likeButton.visibility = View.VISIBLE
            playButton.visibility = View.GONE
        } else {
            likeButton.visibility = View.GONE
            playButton.visibility = View.VISIBLE
        }

        playButton.setOnClickListener {
            itemClickListener?.invoke()
        }

        likeButton.setOnClickListener {
            itemClickListener?.invoke()
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        // Clean up resources, if necessary
    }
}