package com.borna.dotinfilm.sections.presentation.detail.components

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.leanback.widget.Presenter
import com.borna.dotinfilm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class GalleryPresenter : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val imageUrl = item as String
        val imageView = viewHolder.view.findViewById<ImageView>(R.id.film_image)
        Glide.with(viewHolder.view.context).load(imageUrl).transform(RoundedCorners(20)).into(imageView)
    }

    override fun onUnbindViewHolder(p0: ViewHolder) {
    }
}