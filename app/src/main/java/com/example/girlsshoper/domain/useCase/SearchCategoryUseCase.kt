package com.example.girlsshoper.domain.useCase

import com.example.girlsshoper.domain.repo.repo
import javax.inject.Inject

class SearchCategoryUseCase @Inject constructor(private val repo: repo) {
    fun searchCategoryUseCase(searchCategoryQuery : String) = repo.searchCategoryByQuery(searchCategoryQuery = searchCategoryQuery)
}