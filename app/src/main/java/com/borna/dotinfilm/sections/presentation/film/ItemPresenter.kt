package com.borna.dotinfilm.sections.presentation.film

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.borna.dotinfilm.R
import com.borna.dotinfilm.sections.domain.models.Film
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ItemPresenter(val cardType: String) : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val layoutId = if (cardType == "portrait") R.layout.portrait_item_view else R.layout.landscape_item_view
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val film = item as Film
        val title = viewHolder.view.findViewById<TextView>(R.id.film_title)
        title.text = film.name
        val imageView = viewHolder.view.findViewById<ImageView>(R.id.film_image)
        Glide.with(viewHolder.view.context).load(film.imageUrl).transform(RoundedCorners(20)).into(imageView)
    }

    override fun onUnbindViewHolder(p0: ViewHolder) {
    }
}