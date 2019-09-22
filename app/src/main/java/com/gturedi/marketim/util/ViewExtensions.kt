package com.gturedi.marketim.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

// created by @gturedi
fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}

fun ViewGroup.inflate(layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}
