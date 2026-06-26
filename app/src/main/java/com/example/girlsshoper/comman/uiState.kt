package com.example.girlsshoper.comman

import com.example.girlsshoper.domain.module.CartModel
import com.example.girlsshoper.domain.module.bannerPostsModel
import com.example.girlsshoper.domain.module.categoryModel
import com.example.girlsshoper.domain.module.productModel
import com.example.girlsshoper.domain.module.userModel

data class getAllCategoryTYpe(
    val isLoading : Boolean = false,
    val isData : List<categoryModel>? = emptyList(),
    val isError : String? = null
)

data class getAllProductType(
    val isLoading : Boolean = false,
    val isData : List<productModel>? = null,
    val isError : String? = null
)

data class registerUserTYpe(
    val isLoading : Boolean = false,
    val isData : String? = null,
    val isError : String? = null
)
data class loginWithEmailPassType(
    val isLoading : Boolean = false,
    val isData : String? = null,
    val isError : String? = null
)

data class getProductByIDType(
    val isLoading : Boolean = false,
    val isData : productModel? = null,
    val isError : String? = null
)

data class getSpacUserByIdType(
    val isLoading : Boolean = false,
    val isData : userModel? = null,
    val isError : String? = null
)

data class getProductByCategoryType(
    val isLoading: Boolean = false,
    val isData : List<productModel>? = null,
    val isError : String? =  null
)

data class searchProductType(
    val isLoading : Boolean = false,
    val isData : List<productModel>? = null,
    val isError : String? = null
)

data class loadhomeScreenType(
    val isLoading : Boolean = false,
    val isError : String? = null,
    val isCaregoryData : List<categoryModel>? = null,
    val isProductData : List<productModel>? = null,
    val isBannerPostData : List<bannerPostsModel>? = null
)
data class searchCategoryType(
    val isLoading: Boolean = false,
    val isData: List<categoryModel>? = null,
    val isError : String? = null
)
data class getBannerPostsType(
    val isLoading: Boolean = false,
    val isData: List<bannerPostsModel>? = null,
    val isError: String? = null
)


data class remove_upsertCartProductType(
    val isLoading : Boolean = false,
    val isData: String? = null,
    val isError: String? = null
)
data class isProductinCartorNotType(
    val isLoading : Boolean = false,
    val isData : Boolean? = null,
    val isError : String? = null
)
data class getCartProductType(
    val isLoading: Boolean = false,
    val isData: List<CartModel>? = null,
    val isError: String? = null
)