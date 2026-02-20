package com.example.girlsshoper.presentation.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girlsshoper.comman.MainState
import com.example.girlsshoper.comman.getAllCategoryTYpe
import com.example.girlsshoper.comman.getAllProductType
import com.example.girlsshoper.comman.getProductByIDType
import com.example.girlsshoper.comman.getSpacUserByIdType
import com.example.girlsshoper.comman.loadhomeScreenType
import com.example.girlsshoper.comman.loginWithEmailPassType
import com.example.girlsshoper.comman.registerUserTYpe
import com.example.girlsshoper.comman.searchProductType
import com.example.girlsshoper.domain.module.categoryModel
import com.example.girlsshoper.domain.module.productModel
import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.domain.useCase.GetAllCategoryUseCase
import com.example.girlsshoper.domain.useCase.GetAllProductUseCase
import com.example.girlsshoper.domain.useCase.GetProductByIDUseCase
import com.example.girlsshoper.domain.useCase.GetSpacUserByIdUseCase
import com.example.girlsshoper.domain.useCase.LoginWithEmailPassUseCase
import com.example.girlsshoper.domain.useCase.RegisterUserUseCase
import com.example.girlsshoper.domain.useCase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class myVIewModel @Inject constructor(
    private val GetAllCategoryUseCase: GetAllCategoryUseCase,
    private val GetAllProductUseCase: GetAllProductUseCase,
    private val RegisterUserUseCase: RegisterUserUseCase,
    private val LoginWithEmailPassUseCase: LoginWithEmailPassUseCase,
    private val GetProductByIDUseCase: GetProductByIDUseCase,
    private val GetSpacUserByIdUseCase: GetSpacUserByIdUseCase,
    private val SearchProductUseCase : SearchProductUseCase,


    ) : ViewModel(){

    val _loadhomeScreenState = MutableStateFlow(loadhomeScreenType())
    val loadhomeScreenState = _loadhomeScreenState.asStateFlow()

    private val _getAllCategoryState = MutableStateFlow(getAllCategoryTYpe())
    val getAllCategoryState = _getAllCategoryState.asStateFlow()
    private val _getAllProductState = MutableStateFlow(getAllProductType())
    val getAllProductState = _getAllProductState.asStateFlow()
    private val _registerUserState = MutableStateFlow(registerUserTYpe())
    val registerUserState = _registerUserState.asStateFlow()
    private val _loginWithEmailPassState = MutableStateFlow(loginWithEmailPassType())
    val loginWithEmailPassState = _loginWithEmailPassState.asStateFlow()
    private val _getProductByIDState = MutableStateFlow(getProductByIDType())
    val getProductByIDState = _getProductByIDState.asStateFlow()
    private val _getSpacUserByIdState = MutableStateFlow(getSpacUserByIdType())
    val getSpacUserByIdState = _getSpacUserByIdState.asStateFlow()

    private val _searchProductState = MutableStateFlow(searchProductType())
    val searchProductState = _searchProductState.asStateFlow()


    fun loadhomeScreenModel(){
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                GetAllCategoryUseCase.getAllCategoryUseCase(),
                GetAllProductUseCase.getAllProductUseCase()
            ){ categoryResult , productResult ->
                when{
                    categoryResult is MainState.Success && productResult is MainState.Success -> {
                        loadhomeScreenType(
                            isCaregoryData = categoryResult.data,
                            isProductData = productResult.data
                        )
                    }
                    categoryResult is MainState.Error -> {
                        loadhomeScreenType(isError = categoryResult.message)
                    }
                    productResult is MainState.Error -> {
                        loadhomeScreenType(isError = productResult.message)
                    }

                    else -> {
                        loadhomeScreenType(isLoading = true)
                    }

                }
            }.collectLatest {
                _loadhomeScreenState.value = it
            }
        }
    }

    fun getAllCategoryVModel(){
        viewModelScope.launch {
            GetAllCategoryUseCase.getAllCategoryUseCase().collectLatest {
                when(it){
                    is MainState.Success -> { _getAllCategoryState.value = getAllCategoryTYpe(isData = it.data)}
                    is MainState.Error -> { _getAllCategoryState.value = getAllCategoryTYpe(isError = it.message) }
                    is MainState.Loading -> { _getAllCategoryState.value = getAllCategoryTYpe(isLoading = true) }
                }
            }

        }
    }

    fun getAllProductVModel(){
        viewModelScope.launch {
            GetAllProductUseCase.getAllProductUseCase().collectLatest {
                when(it){
                    is MainState.Success -> {
                        _getAllProductState.value = getAllProductType(isData = it.data)
                    }
                    is MainState.Error -> {
                        _getAllProductState.value = getAllProductType(isError = it.message)
                    }
                    is MainState.Loading -> {
                        _getAllProductState.value = getAllProductType(isLoading = true)
                    }
                }
            }
        }
    }

    fun registerUserVModel(userData: userModel){
        viewModelScope.launch(Dispatchers.IO) {
            RegisterUserUseCase.registerUserUseCase(userData).collectLatest {
                when(it){
                    is MainState.Success -> {
                        registerUserTYpe(isData = it.data)
                    }
                    is MainState.Error -> {
                        registerUserTYpe(isError = it.message)
                    }
                    is MainState.Loading -> {
                        registerUserTYpe(isLoading = true)
                    }
                }
            }
        }
    }

    fun loginWithEmailAndPassVModel(
        email : String,
        password : String
    ){
        viewModelScope.launch {
            LoginWithEmailPassUseCase.loginWithEmailPassUseCase(email = email, password = password).collectLatest {
                when(it){
                    is MainState.Success -> {
                        _loginWithEmailPassState.value = loginWithEmailPassType(isData = it.data)
                    }
                    is MainState.Error -> {
                        _loginWithEmailPassState.value = loginWithEmailPassType(isError = it.message)
                    }
                    is MainState.Loading -> {
                        _loginWithEmailPassState.value = loginWithEmailPassType(isLoading = true)
                    }
                }
            }
        }

    }

    fun getProductByIDVModel(productId : String){
        viewModelScope.launch {
            GetProductByIDUseCase.getProductByIDUseCase(productId = productId).collectLatest {
                when(it){
                    is MainState.Success -> {
                        _getProductByIDState.value = getProductByIDType(isData = it.data)
                    }
                    is MainState.Error -> {
                        _getProductByIDState.value = getProductByIDType(isError = it.message)
                    }
                    is MainState.Loading -> {
                        _getProductByIDState.value = getProductByIDType(isLoading = true)
                    }
                }
            }
        }
    }

    fun getSpacUserByIdVModel(userId : String){
        viewModelScope.launch {
            GetSpacUserByIdUseCase.getSpacUserByIdUseCase(userId = userId).collectLatest {
                when(it){
                    is MainState.Loading -> { _getSpacUserByIdState.value = getSpacUserByIdType(isLoading = true) }
                    is MainState.Success -> { _getSpacUserByIdState.value = getSpacUserByIdType(isData = it.data) }
                    is MainState.Error -> { _getSpacUserByIdState.value = getSpacUserByIdType(isError = it.message) }
                }
            }
        }
    }

    val _searchQuery = MutableStateFlow("")
    fun onSearchQueryChange(searchQuery: String){
        _searchQuery.value = searchQuery
    }

    fun onSearchQuery(){
        viewModelScope.launch {
            _searchQuery.debounce(500L).distinctUntilChanged()
                .collectLatest {
                    if (it.isNotEmpty()){
                        searchProductVModel(searchQuery = it)

                    }
                }
        }
    }

    fun searchProductVModel(searchQuery: String){
        viewModelScope.launch {
            SearchProductUseCase.searchProductUseCase(searchQuery = searchQuery).collectLatest {
                when(it){
                    is MainState.Loading -> { _searchProductState.value = searchProductType(isLoading = true) }
                    is MainState.Success -> {_searchProductState.value = searchProductType(isData = it.data) }
                    is MainState.Error -> {_searchProductState.value = searchProductType(isError = it.message) }
                }
            }
        }

    }






    fun resetRegisterUserState(){
        _registerUserState.value = registerUserTYpe()
    }
    fun resetLoginWithEmailPassState(){
        _loginWithEmailPassState.value = loginWithEmailPassType()
    }




}

fun String.toColor(): Color {
    return try {
        Color(android.graphics.Color.parseColor(this))
    } catch (e: Exception) {
        Color.Black // fallback color
    }
}