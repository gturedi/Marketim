package com.gturedi.marketim

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