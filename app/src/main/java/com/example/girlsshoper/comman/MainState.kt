package com.example.girlsshoper.comman

import com.example.girlsshoper.domain.module.productModel

sealed class MainState<out T> {
    object Loading : MainState<Nothing>()
    data class Success<out T>(val data: T) : MainState<T>()
    data class Error(val message: String) : MainState<Nothing>()
}
