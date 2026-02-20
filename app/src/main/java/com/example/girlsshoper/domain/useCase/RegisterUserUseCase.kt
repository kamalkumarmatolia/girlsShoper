package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class RegisterUserUseCase @Inject constructor(private val repo: repo) {
    fun registerUserUseCase(userData : userModel) = repo.registerUserEmailPass(userData)
}