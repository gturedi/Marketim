package com.gturedi.marketim

import android.content.Context

// created by @gturedi at 9/21/19
class PreferencesService(context: Context) {

    val PREF_NAME = "pref"
    val KEY_REMEMBER_ME = "rememberMe"
    val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    var rememberMe:Boolean
    get() = prefs.getBoolean(KEY_REMEMBER_ME, false)
    set(value) = prefs.edit().putBoolean(KEY_REMEMBER_ME, value).apply()

}