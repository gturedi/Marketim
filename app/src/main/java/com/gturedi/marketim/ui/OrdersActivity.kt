package com.gturedi.marketim.ui

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gturedi.marketim.BaseActivity
import com.gturedi.marketim.R
import com.gturedi.marketim.service.OrdersResponseState
import com.gturedi.marketim.service.PreferencesService
import com.gturedi.marketim.ui.adapter.OrdersAdapter
import com.gturedi.marketim.util.str
import kotlinx.android.synthetic.main.activity_orders.*
import timber.log.Timber
import java.net.UnknownHostException

class OrdersActivity : BaseActivity() {

    companion object {
        fun create(context: Context) = Intent(context, OrdersActivity::class.java)
    }

    private val viewModel: OrdersViewModel by lazy {
        ViewModelProviders.of(this).get(OrdersViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_orders)

        title = str(R.string.app_name).toUpperCase()

        btnQuit.setOnClickListener {
            Timber.i("btnQuitClicked")
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

        viewModel.items.observe(this, Observer { result ->
            Timber.i("loginResult:" + result)
            hideLoading()
            when (result) {
                is OrdersResponseState.Data -> rvItems.adapter = OrdersAdapter(result.list)
                is OrdersResponseState.Error -> {
                    val msg =
                        if (result.throwable is UnknownHostException) R.string.connectionErrorMessage else R.string.generalErrorMessage
                    AlertDialog.Builder(this@OrdersActivity)
                        .setMessage(msg)
                        .setCancelable(false)
                        .setNegativeButton(android.R.string.cancel) { _, _ -> finish() }
                        .setPositiveButton(R.string.tryAgain) { _, _ -> sendRequest() }
                        .show()
                }
            }
        })

        sendRequest()
    }

    private fun sendRequest() {
        showLoading()
        viewModel.getItems()
    }

}