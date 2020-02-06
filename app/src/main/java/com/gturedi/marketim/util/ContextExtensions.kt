package com.gturedi.marketim.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes

// created by @gturedi at 9/20/19
fun Context.toast(@StringRes message: Int) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.toast(message: String) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun Context.str(@StringRes resId: Int) =
    this.getString(resId)