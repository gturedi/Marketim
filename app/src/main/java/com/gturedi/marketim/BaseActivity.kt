package com.gturedi.marketim

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    var progressDialog: ProgressDialog? = null

    fun showLoading(): Unit {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(this).apply {
                setMessage(getString(R.string.loading))
            }
        }
        progressDialog?.apply { show() }
    }

    fun hideLoading(): Unit {
        progressDialog?.apply { dismiss() }
    }

}