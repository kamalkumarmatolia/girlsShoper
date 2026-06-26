package com.example.girlsshoper.presentation.viewModel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.girlsshoper.comman.MainState
import com.example.girlsshoper.comman.getAllCategoryTYpe
import com.example.girlsshoper.comman.getAllProductType
import com.example.girlsshoper.comman.getCartProductType
import com.example.girlsshoper.comman.getProductByIDType
import com.example.girlsshoper.comman.getSpacUserByIdType
import com.example.girlsshoper.comman.isProductinCartorNotType
import com.example.girlsshoper.comman.loadhomeScreenType
import com.example.girlsshoper.comman.loginWithEmailPassType
import com.example.girlsshoper.comman.registerUserTYpe
import com.example.girlsshoper.comman.remove_upsertCartProductType
import com.example.girlsshoper.comman.searchCategoryType
import com.example.girlsshoper.comman.searchProductType
import com.example.girlsshoper.domain.module.CartModel
import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.domain.useCase.CartUseCase
import com.example.girlsshoper.domain.useCase.GetAllCategoryUseCase
import com.example.girlsshoper.domain.useCase.GetAllProductUseCase
import com.example.girlsshoper.domain.useCase.GetBannerPostUseCase
import com.example.girlsshoper.domain.useCase.GetProductByCategoryUseCase
import com.example.girlsshoper.domain.useCase.GetProductByIDUseCase
import com.example.girlsshoper.domain.useCase.GetSpacUserByIdUseCase
import com.example.girlsshoper.domain.useCase.LoginWithEmailPassUseCase
import com.example.girlsshoper.domain.useCase.RegisterUserUseCase
import com.example.girlsshoper.domain.useCase.SearchCategoryUseCase
import com.example.girlsshoper.domain.useCase.SearchProductUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
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
    private val GetBannerPostUseCase : GetBannerPostUseCase,
    private val SearchCategoryUseCase : SearchCategoryUseCase,
    private val GetProductByCategoryUseCase : GetProductByCategoryUseCase,
    private val CartUseCase : CartUseCase


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
    private val _searchCategoryState = MutableStateFlow(searchCategoryType())
    val searchCategoryState = _searchCategoryState.asStateFlow()
    private val _getProductByCategorystate = MutableStateFlow(getAllProductType())
    val getProductByCategorystate = _getProductByCategorystate.asStateFlow()
    private val _addtoCartState = MutableStateFlow(remove_upsertCartProductType())
    val addtoCartState = _addtoCartState.asStateFlow()
    private val _removeFromCartState = MutableStateFlow(remove_upsertCartProductType())
    val removeFromCartState = _removeFromCartState.asStateFlow()
    private val _updateCartQuantityState = MutableStateFlow(remove_upsertCartProductType())
    val updateCartQuantityState = _updateCartQuantityState.asStateFlow()
    private val _isProductinCartorNotState = MutableStateFlow(isProductinCartorNotType())
    val isProductinCartorNotState = _isProductinCartorNotState.asStateFlow()
    private val _getCartProductState = MutableStateFlow(getCartProductType())
    val getCartProductState = _getCartProductState.asStateFlow()




    fun loadhomeScreenModel(){
        viewModelScope.launch(Dispatchers.IO) {
            combine(
                GetAllCategoryUseCase.getAllCategoryUseCase(),
                GetAllProductUseCase.getAllProductUseCase(),
                GetBannerPostUseCase.getBannerPostUseCase()
            ){ categoryResult , productResult, bannerPostResult ->
                when{
                    categoryResult is MainState.Success && productResult is MainState.Success && bannerPostResult is MainState.Success-> {
                        loadhomeScreenType(
                            isCaregoryData = categoryResult.data,
                            isProductData = productResult.data,
                            isBannerPostData = bannerPostResult.data
                        )
                    }
                    categoryResult is MainState.Error -> {
                        loadhomeScreenType(isError = categoryResult.message)
                    }
                    productResult is MainState.Error -> {
                        loadhomeScreenType(isError = productResult.message)
                    }
                    bannerPostResult is MainState.Error -> {
                        loadhomeScreenType(isError = bannerPostResult.message)
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

    fun getProductByCategoryVModel(categoryName : String){
        viewModelScope.launch {
            GetProductByCategoryUseCase.getProductByCategoryUseCase(categoryName = categoryName).collectLatest {
                when(it){
                    is MainState.Loading -> { _getProductByCategorystate.value = getAllProductType(isLoading = true) }
                    is MainState.Success -> { _getProductByCategorystate.value = getAllProductType(isData = it.data) }
                    is MainState.Error -> { _getProductByCategorystate.value = getAllProductType(isError = it.message) }
                }
            }
        }
    }

    fun addtoCartVModel(cartModel: CartModel){
        viewModelScope.launch {
            CartUseCase.addtoCartUseCase(cartModel = cartModel).collectLatest {
                when(it){
                    is MainState.Loading -> {_addtoCartState.value = remove_upsertCartProductType(isLoading = true) }
                    is MainState.Success -> {_addtoCartState.value = remove_upsertCartProductType(isData = it.data) }
                    is MainState.Error -> {_addtoCartState.value = remove_upsertCartProductType(isError = it.message) }
                }
            }
        }
    }
    fun removeFromCartVModel(productid : String){
        viewModelScope.launch {
            CartUseCase.removeFromCartUseCase(productid = productid).collectLatest {
                when(it){
                    is MainState.Loading -> {_removeFromCartState.value = remove_upsertCartProductType(isLoading = true) }
                    is MainState.Success -> {_removeFromCartState.value = remove_upsertCartProductType(isData = it.data) }
                    is MainState.Error -> {_removeFromCartState.value = remove_upsertCartProductType(isError = it.message) }
                }
            }
        }
    }
    fun updateCartQuantityVModel(productid : String, increase : Boolean){
        viewModelScope.launch {
            CartUseCase.updateCartQuantityUseCase(productid = productid, increase = increase).collectLatest {
                when(it){
                    is MainState.Loading -> {_updateCartQuantityState.value = remove_upsertCartProductType(isLoading = true) }
                    is MainState.Success -> {_updateCartQuantityState.value = remove_upsertCartProductType(isData = it.data) }
                    is MainState.Error -> {_updateCartQuantityState.value = remove_upsertCartProductType(isError = it.message) }
                }
            }
        }
    }

    fun isProductinCartorNotVModel(productid : String){
        viewModelScope.launch {
            CartUseCase.isProductinCartorNotUseCase(productid = productid).collectLatest {
                when(it){
                    is MainState.Loading -> {_isProductinCartorNotState.value = isProductinCartorNotType(isLoading = true) }
                    is MainState.Success -> {_isProductinCartorNotState.value = isProductinCartorNotType(isData = it.data) }
                    is MainState.Error -> {_isProductinCartorNotState.value = isProductinCartorNotType(isError = it.message) }
                }
            }
        }
    }

    fun getCartProductVModel(){
        viewModelScope.launch {
            CartUseCase.getCartProductUseCase().collectLatest {
                when(it){
                    is MainState.Loading -> {_getCartProductState.value = getCartProductType(isLoading = true) }
                    is MainState.Success -> {_getCartProductState.value = getCartProductType(isData = it.data) }
                    is MainState.Error -> {_getCartProductState.value = getCartProductType(isError = it.message) }
                }
            }
        }
    }



    val _searchQuery = MutableStateFlow("")
    val _categorySearchQuery = MutableStateFlow("")
    fun onSearchQueryChange(searchQuery: String){
        _searchQuery.value = searchQuery
    }
    fun onCategorySearchQueryChange(categoryQuery: String){
        _categorySearchQuery.value = categoryQuery
    }




    fun onSearchQuery(){
        viewModelScope.launch {
            _searchQuery.debounce(300L).distinctUntilChanged()
                .collectLatest {
                    if (it.isNotEmpty()){
                        searchProductVModel(searchQuery = it)

                    }
                }
        }
    }

    fun onCategorySearchQuery(){
        viewModelScope.launch {
            _categorySearchQuery.debounce { 500L }.distinctUntilChanged()
                .collectLatest {
                    if (it.isNotEmpty()){
                        searchCategoryModel(searchCategoryQuery = it)
                    }
                }
        }
    }

    init {
        onSearchQuery()
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
    fun searchCategoryModel(searchCategoryQuery :String){
        viewModelScope.launch {
            SearchCategoryUseCase.searchCategoryUseCase(searchCategoryQuery = searchCategoryQuery).collectLatest {
                when(it){
                    is MainState.Loading -> {_searchCategoryState.value = searchCategoryType(isLoading = true) }
                    is MainState.Success -> {_searchCategoryState.value = searchCategoryType(isData = it.data) }
                    is MainState.Error -> {_searchCategoryState.value = searchCategoryType(isError = it.message) }
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