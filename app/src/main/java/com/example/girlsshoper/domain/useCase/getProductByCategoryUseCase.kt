package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class GetProductByCategoryUseCase @Inject constructor(private val repo: repo){
    fun getProductByCategoryUseCase(categoryName : String) = repo.getProductByCategory(categoryName = categoryName)
}