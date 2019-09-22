package com.gturedi.marketim

// created by @gturedi at 9/22/19
interface ResponseListener<T> {

    fun onSuccess(data:T)

    fun onFailure(e: Throwable)

}