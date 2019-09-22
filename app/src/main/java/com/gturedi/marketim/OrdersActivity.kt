package com.gturedi.marketim

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_orders.*

class OrdersActivity : BaseActivity() {

    companion object {
        fun create(context: Context) = Intent(context, OrdersActivity::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        btnQuit.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle(R.string.quitBtn)
                .setMessage(R.string.quitMessage)
                .setNegativeButton(android.R.string.cancel, null)
                .setPositiveButton(android.R.string.ok) { _, _ ->
                    PreferencesService(this).rememberMe = false
                    finish()
                }
                .show()
        }

        sendRequest()
    }

    private fun sendRequest() {
        showLoading()
        OrdersService().getItemsAsync(object : ResponseListener<List<OrderModel>> {
            override fun onSuccess(data: List<OrderModel>) {
                hideLoading()
                rvItems.adapter = OrdersAdpater(data)
            }

            override fun onFailure(e: Throwable) {
                hideLoading()
                AlertDialog.Builder(this@OrdersActivity)
                    .setMessage(e.message)
                    .setNegativeButton(android.R.string.cancel, null)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        sendRequest()
                    }
                    .show()
            }

        })
    }

}