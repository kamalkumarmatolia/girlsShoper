package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class GetAllCategoryUseCase @Inject constructor(private val repo: repo){
    fun getAllCategoryUseCase() = repo.getAllCategoryRepo()
}