package com.example.girlsshoper.presentation.navigation

import com.example.girlsshoper.domain.module.productColorLists
import kotlinx.serialization.Serializable

sealed class subNavigation {
    @Serializable
    object mainhomeScreen : subNavigation()

    @Serializable
    object loginSingupScreen : subNavigation()

}

sealed class Routes{

    @Serializable
    object loginScreen

    @Serializable
    object shopingCartScreen

    @Serializable
    object singupScreen

    @Serializable
    object homeScreen

    @Serializable
    object eachCategoryScreen

    @Serializable
    object cartScreen

    @Serializable
    object wishlistScreen

    @Serializable
    object profileScreen

    @Serializable
    data class productDetailedScreen(val productId : String)

    @Serializable
    data class shipingScreenScreen(
        val productID : String,
        val productQuintity : Int,
        val productColorCode : String,
        val productColorName : String,
        val productSize : String
    )

    @Serializable
    object seemoreProduct

    @Serializable
    object seeAllCategoryScreen

    @Serializable
    object paymentScreen

    @Serializable
    object checkOutScreen

}




