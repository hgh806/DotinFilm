package com.borna.dotinfilm.sections.presentation.film

import android.content.ContentValues.TAG
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.leanback.widget.Presenter
import com.borna.dotinfilm.R
import com.borna.dotinfilm.sections.domain.models.Film
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners

class ItemPresenter(val cardType: String, val likeChangeCallBack: (Film) -> Unit ) : Presenter() {
    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val layoutId = if (cardType == "portrait") R.layout.portrait_item_view else R.layout.landscape_item_view
        val view = LayoutInflater.from(parent.context).inflate(layoutId, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val film = item as Film
        val title = viewHolder.view.findViewById<TextView>(R.id.film_title)
        val badgeImage = viewHolder.view.findViewById<ImageView>(R.id.like_image)

        title.text = film.name
        if (badgeImage != null) {
            Log.i(TAG, "onBindViewHolder: showBadge: ${film.showBadge}")
            val visibility = if (film.showBadge) View.VISIBLE else View.GONE
            badgeImage.visibility = visibility

            Handler(Looper.getMainLooper()).postDelayed({
                likeChangeCallBack(film)
                badgeImage.visibility = View.GONE
            }, 3000)
        }
        val imageView = viewHolder.view.findViewById<ImageView>(R.id.film_image)
        Glide.with(viewHolder.view.context).load(film.imageUrl).transform(RoundedCorners(20)).into(imageView)
    }

    override fun onUnbindViewHolder(p0: ViewHolder) {
    }
}