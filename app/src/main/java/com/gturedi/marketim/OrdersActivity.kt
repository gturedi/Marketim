package com.gturedi.marketim

import android.content.Context
import android.content.Intent
import android.os.Bundle

class OrdersActivity : BaseActivity() {

    companion object {
        fun create(context: Context) = Intent(context, OrdersActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)
    }

}