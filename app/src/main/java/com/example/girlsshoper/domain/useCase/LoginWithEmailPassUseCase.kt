package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class LoginWithEmailPassUseCase @Inject constructor(private val repo: repo) {
    fun loginWithEmailPassUseCase(email: String, password: String) = repo.loginwithemailpass(email, password)
}