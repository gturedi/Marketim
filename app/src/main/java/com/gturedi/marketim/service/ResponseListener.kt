package com.gturedi.marketim.service

// created by @gturedi at 9/22/19
interface ResponseListener<T> {

    fun onSuccess(data:T)

    fun onFailure(e: Throwable)

}