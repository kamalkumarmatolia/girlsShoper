package com.example.girlsshoper.domain.repo

import android.net.Uri
import com.example.girlsshoper.comman.MainState
import com.example.girlsshoper.domain.module.CartModel
import com.example.girlsshoper.domain.module.bannerPostsModel
import com.example.girlsshoper.domain.module.categoryModel
import com.example.girlsshoper.domain.module.productModel
import com.example.girlsshoper.domain.module.userModel
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.descriptors.PrimitiveKind


interface repo {

    fun getAllCategoryRepo(): Flow<MainState<List<categoryModel>>>
    fun getAllProductRepo(): Flow<MainState<List<productModel>>>
    fun registerUserEmailPass(userData : userModel): Flow<MainState<String>>
    fun loginwithemailpass(email: String, password: String): Flow<MainState<String>>
    //    fun getProductRepoLimit() : Flow<MainState<List<categoryModel>>>
//    fun getCategoryRepoLimit(): Flow<MainState<List<productModel>>>
    fun getProductById(productId :String) : Flow<MainState<productModel>>
    fun getSpacUserById(userId : String) : Flow<MainState<userModel>>
    fun updateUserProfile(userData : userModel) : Flow<MainState<String>>
    fun searchProductByQuery(searchQuery : String) : Flow<MainState<List<productModel>>>
    fun searchCategoryByQuery(searchCategoryQuery : String) : Flow<MainState<List<categoryModel>>>
    fun getBannerPostsRepo() : Flow<MainState<List<bannerPostsModel>>>

    suspend fun updateFromToken(userID : String)
    fun getProductByCategory(categoryName : String)  : Flow<MainState<List<productModel>>>
    fun addtoCart(cartModel: CartModel) : Flow<MainState<String>>
    fun removeFromCart(productid : String) : Flow<MainState<String>>
    fun updateCartQuantity(productid : String, increses : Boolean) : Flow<MainState<String>>
    fun isProductinCartorNot(productid: String) : Flow<MainState<Boolean>>
    fun getCartProduct() : Flow<MainState<List<CartModel>>>
    fun updateUserInfo(userModel: userModel) : Flow<MainState<String>>
    fun changeUserImage(imageUri : Uri) : Flow<MainState<String>>




}