package com.gturedi.marketim.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.gturedi.marketim.BaseActivity
import com.gturedi.marketim.R
import com.gturedi.marketim.service.OrderModel
import com.gturedi.marketim.service.OrdersService
import com.gturedi.marketim.service.PreferencesService
import com.gturedi.marketim.service.ResponseListener
import com.gturedi.marketim.ui.adapter.OrdersAdapter
import kotlinx.android.synthetic.main.activity_orders.*
import java.net.UnknownHostException

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
        OrdersService().getItemsAsync(object :
            ResponseListener<List<OrderModel>> {
            override fun onSuccess(data: List<OrderModel>) {
                hideLoading()
                rvItems.adapter = OrdersAdapter(data)
            }

            override fun onFailure(e: Throwable) {
                hideLoading()
                val msg = if (e is UnknownHostException) R.string.connectionErrorMessage else R.string.generalErrorMessage
                AlertDialog.Builder(this@OrdersActivity)
                    .setMessage(msg)
                    .setCancelable(false)
                    .setNegativeButton(android.R.string.cancel) { _, _ -> finish() }
                    .setPositiveButton(R.string.tryAgain) { _, _ -> sendRequest() }
                    .show()
            }

        })
    }

}