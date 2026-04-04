package com.example.girlsshoper.domain.module

data class CartModel(
    val productId: String = "",
    val productTitle: String = "",
    val price: Double = 0.0,
    val imageUrl: String = "",
    val quantity: Int = 1,
    val productSize : List<String> = emptyList(),
    val productColor : List<productColorLists> = emptyList(),
    val category : String = "",
    val date: Long = System.currentTimeMillis()

)