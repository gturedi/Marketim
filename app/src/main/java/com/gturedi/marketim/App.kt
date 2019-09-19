package com.gturedi.marketim

import android.app.Application
import timber.log.Timber

// created by @gturedi at 9/20/19
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.i("onCreate")
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}
