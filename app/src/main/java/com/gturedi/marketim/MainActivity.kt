package com.gturedi.marketim

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnLogin.setOnClickListener {
            when {
                tvUserName.text.isEmpty() -> toast(R.string.loading)
                tvPassword.text.isEmpty() -> toast(R.string.loading)
                else -> showLoading()
            }
        }
    }

}
