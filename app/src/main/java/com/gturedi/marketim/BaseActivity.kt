package com.gturedi.marketim

import android.app.ProgressDialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.i("onCreate:"+javaClass.simpleName)
        super.onCreate(savedInstanceState)

        progressDialog = ProgressDialog(this).apply {
            setMessage(getString(R.string.loading))
        }
    }

    fun showLoading() {
        progressDialog?.show()
    }

    fun hideLoading() {
        progressDialog?.dismiss()
    }

    override fun onDestroy() {
        Timber.i("onDestroy:"+javaClass.simpleName)
        super.onDestroy()
    }

}