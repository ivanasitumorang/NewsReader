package com.azuka.newsreader

import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.azuka.newsreader.network.NewsApiStatus
import com.squareup.picasso.Picasso

@BindingAdapter("newsApiStatus")
fun bindStatusIndicator(statusIndicatorImageView: ImageView, status: NewsApiStatus?){
    when (status){
        NewsApiStatus.LOADING -> {
            statusIndicatorImageView.visibility = View.VISIBLE
            statusIndicatorImageView.setImageResource(R.drawable.loading_animation)
        }
        NewsApiStatus.ERROR -> {
            statusIndicatorImageView.visibility = View.VISIBLE
            statusIndicatorImageView.setImageResource(R.drawable.ic_error)
        }
        NewsApiStatus.DONE -> {
            statusIndicatorImageView.visibility = View.GONE
        }
    }
}

@BindingAdapter("newsApiStatus")
fun bindRefresh(refreshIndicator: TextView, status: NewsApiStatus?){
    when (status){
        NewsApiStatus.LOADING -> {
            refreshIndicator.visibility = View.GONE
        }
        NewsApiStatus.ERROR -> {
            refreshIndicator.visibility = View.VISIBLE
        }
        NewsApiStatus.DONE -> {
            refreshIndicator.visibility = View.GONE
        }
    }
}

@BindingAdapter("imageUrl", "placeholder", "error")
fun loadImage(imageView: ImageView, url: String, placeholder: Drawable, error: Drawable){
    Picasso.get()
        .load(url)
        .placeholder(placeholder)
        .error(error)
        .into(imageView)
}