package com.gturedi.marketim.service

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.gturedi.marketim.BuildConfig
import com.gturedi.marketim.util.SERVICE_URL
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor

// created by @gturedi at 9/22/19
object OrdersService {

    @Throws(Exception::class)
    fun getItems() : List<OrderModel> {
        val logging = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val request = Request.Builder()
            .url(SERVICE_URL)
            .build()

        val response = client.newCall(request).execute()

        if (!response.isSuccessful) throw IllegalStateException("err:" + response.code())

        val listType = object : TypeToken<List<OrderModel>>() {}.type
        return Gson().fromJson(response.body()?.string(), listType)
    }

}