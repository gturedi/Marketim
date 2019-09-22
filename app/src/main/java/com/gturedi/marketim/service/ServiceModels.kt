package com.gturedi.marketim.service

data class OrderModel(
    val date: Int,
    val month: Int,
    val marketName: String,
    val orderName: String,
    val productPrice: Double,
    val productState: String,
    val productDetail: ProductModel
)

class ProductModel(
    val orderDetail: String,
    val summaryPrice: Double
)

sealed class OrdersResponseState {
    data class Error(val throwable: Throwable) : OrdersResponseState()
    data class Data(val list: List<OrderModel>) : OrdersResponseState()
}