package com.gturedi.marketim.service

import android.os.Handler
import android.os.Looper
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gturedi.marketim.BuildConfig
import com.gturedi.marketim.util.SERVICE_URL
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException


// created by @gturedi at 9/22/19
class OrdersService {

    fun getItemsAsync(listener: ResponseListener<List<OrderModel>>) {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val request = Request.Builder()
            .url(SERVICE_URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) = if (response.isSuccessful) {
                runOnMainThread {
                    val listType = object : TypeToken<List<OrderModel>>() {}.type
                    listener.onSuccess(Gson().fromJson(response.body?.string(), listType))
                }
            } else {
                runOnMainThread {
                    listener.onFailure(IllegalStateException("err:" + response.code))
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                runOnMainThread {
                    listener.onFailure(IllegalStateException(e))
                }
            }
        })
    }

    private fun runOnMainThread(runnable: () -> Unit) {
        Handler(Looper.getMainLooper()).post(runnable)
    }


}