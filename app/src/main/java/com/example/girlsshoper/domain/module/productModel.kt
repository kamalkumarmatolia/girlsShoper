package com.example.girlsshoper.domain.module

import androidx.compose.ui.graphics.Color



data class productModel(
    var productTitle : String = "",
    var description : String = "",
    var wenderName : String = "",
    var productSize : List<String> = emptyList(),
    var productColor : List<productColorLists> = emptyList(),
    var productRating : Float = 0f,
    var price : String = "",
    var finalPrice : String = "",
    var imageUrl : String = "",
    var category : String = "",
    var date : Long = System.currentTimeMillis(),
    var abalibilibalUnits : Int = 0,
    var isAvailable : Boolean = false,
    var productId : String = ""
)

data class productColorLists(
    var colorName: String = "",
    var colorCode: String = "#000000"  // Firebase me HEX string store hoga
)


