package com.gturedi.marketim.service

import androidx.annotation.ColorRes
import com.google.gson.annotations.SerializedName
import com.gturedi.marketim.R

data class OrderModel(
    val date: Int,
    val month: Int,
    val marketName: String,
    val orderName: String,
    val productPrice: Double,
    //val productState: String,
    val productState: OrderState,
    val productDetail: ProductModel
)

enum class OrderState(val text:String) {
    @SerializedName("Yolda")
    CARGO("Yolda"),

    @SerializedName("Hazırlanıyor")
    PREPARING("Hazırlanıyor"),

    @SerializedName("Onay Bekliyor")
    APPROVAL("Onay Bekliyor");

    @ColorRes
    fun getColor() = when(this) {
        CARGO -> R.color.stateCargo
        PREPARING -> R.color.statePreparing
        APPROVAL -> R.color.stateApproval
    }
}

class ProductModel(
    val orderDetail: String,
    val summaryPrice: Double
)

sealed class OrdersResponseState {
    data class Error(val throwable: Throwable) : OrdersResponseState()
    data class Data(val list: List<OrderModel>) : OrdersResponseState()
}