package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class GetSpacUserByIdUseCase @Inject constructor(private val repo: repo) {
    fun getSpacUserByIdUseCase(userId : String)  = repo.getSpacUserById(userId = userId)
}