package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.module.CartModel
import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class CartUseCase @Inject constructor(private val repo: repo) {

    fun addtoCartUseCase(cartModel: CartModel) = repo.addtoCart(cartModel = cartModel)
    fun removeFromCartUseCase(productid : String) = repo.removeFromCart(productid = productid)
    fun updateCartQuantityUseCase(productid : String, increase : Boolean) = repo.updateCartQuantity(productid = productid, increses = increase)
    fun isProductinCartorNotUseCase(productid: String) = repo.isProductinCartorNot(productid = productid)
    fun getCartProductUseCase() = repo.getCartProduct()

}