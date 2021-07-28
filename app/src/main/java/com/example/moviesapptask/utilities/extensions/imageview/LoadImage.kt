package com.example.moviesapptask.utilities.extensions.imageview

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide

enum class ImageViewMode {
    NORMAL
}

fun ImageView.load(url: String, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(url)
    if (mode == ImageViewMode.NORMAL) load.into(this)
}

fun ImageView.load(uri: Uri, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(uri)
    if (mode == ImageViewMode.NORMAL) load.into(this)
}

fun ImageView.load(resourceId: Int, mode: ImageViewMode = ImageViewMode.NORMAL) {
    val load = Glide.with(context).load(resourceId)
    if (mode == ImageViewMode.NORMAL) load.into(this)
}