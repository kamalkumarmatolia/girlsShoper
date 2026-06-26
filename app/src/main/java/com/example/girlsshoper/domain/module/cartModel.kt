package com.example.girlsshoper.domain.module

data class CartModel(
    var productId: String = "",
    val productTitle: String = "",
    val price: String = "",
    val imageUrl: String = "",
    val quantity: Int = 1,
    val productSize : String = "",
    val productColor : String = "",
    val category : String = "",
    val date: Long = System.currentTimeMillis()

)