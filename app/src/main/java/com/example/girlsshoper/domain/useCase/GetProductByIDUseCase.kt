package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class GetProductByIDUseCase @Inject constructor(private val repo: repo) {
    fun getProductByIDUseCase(productId : String) = repo.getProductById(productId = productId)

}