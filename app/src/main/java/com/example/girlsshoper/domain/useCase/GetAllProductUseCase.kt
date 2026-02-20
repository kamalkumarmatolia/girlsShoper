package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class GetAllProductUseCase @Inject constructor(private val repo: repo) {
    fun getAllProductUseCase() = repo.getAllProductRepo()
}