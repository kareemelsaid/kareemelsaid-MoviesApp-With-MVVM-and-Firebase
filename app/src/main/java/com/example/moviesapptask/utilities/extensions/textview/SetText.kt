package com.example.moviesapptask.utilities.extensions.textview

import android.graphics.Typeface
import android.view.View
import android.widget.TextView

fun TextView.text(text: String) {
    this.visibility = View.VISIBLE
    this.text = text
}

fun TextView.setFont(fontPath: String) {
    val typeFace = Typeface.createFromAsset(this.context.assets, fontPath)
    this.typeface = typeFace
}