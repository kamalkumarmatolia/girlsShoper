package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.data.repoImpl.repoImpl
import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class SearchProductUseCase @Inject  constructor(private val repo: repo ) {
    fun searchProductUseCase(searchQuery : String)  = repo.searchProductByQuery(searchQuery = searchQuery)
}