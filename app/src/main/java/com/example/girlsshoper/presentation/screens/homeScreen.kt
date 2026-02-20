package com.example.girlsshoper.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import com.example.girlsshoper.R
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.comman.searchProductType
import com.example.girlsshoper.domain.module.categoryModel
import com.example.girlsshoper.domain.module.productModel
import com.example.girlsshoper.presentation.components.card.categoryCard
import com.example.girlsshoper.presentation.components.card.eachProductViewCard
import com.example.girlsshoper.presentation.components.card.productHomeShowCard
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.presentation.navigation.Routes
import com.example.girlsshoper.presentation.viewModel.myVIewModel

@Composable
fun homeScreenUi(
    vIewModel: myVIewModel = hiltViewModel(),
    navcontroller: NavHostController
) {

    val loadhomeScreenState = vIewModel.loadhomeScreenState.collectAsState()



    LaunchedEffect(key1 = Unit) {
        vIewModel.loadhomeScreenModel()
        vIewModel.onSearchQuery()

    }

    when{
        loadhomeScreenState.value.isLoading -> {
            CircularProgressIndicator()
        }

        loadhomeScreenState.value.isError != null -> {
            Text(text = loadhomeScreenState.value.isError!!)
        }
        loadhomeScreenState.value.isCaregoryData != null && loadhomeScreenState.value.isProductData != null ->{
            Log.d("categoryData", "homeScreen: ${loadhomeScreenState.value.isCaregoryData}")
            homeScreen(
                getAllCategoryData = loadhomeScreenState.value.isCaregoryData!!,
                getAllProductData = loadhomeScreenState.value.isProductData!!,
                navcontroller = navcontroller,
                vIewModel = vIewModel

            )
        }


    }
}



@Composable
fun homeScreen(
    getAllCategoryData : List<categoryModel>,
    getAllProductData : List<productModel>,
    navcontroller: NavHostController,
    vIewModel: myVIewModel


) {
    val context = LocalContext.current
    val searchQuery = remember { mutableStateOf("") }
    val searchProductState = vIewModel.searchProductState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize().background(Color.White).verticalScroll(rememberScrollState()).padding(PaddingValues(horizontal = 18.5.dp))

    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            outlineTextFieldComponent(
                value = searchQuery.value,
                onValueChange = {
                    searchQuery.value = it
                    vIewModel.onSearchQueryChange(it) },
                label = "sarch",
                modifier = Modifier.weight(1.8f)

            )
            IconButton(
                onClick = {}
            ) {
                Icon(
                    modifier = Modifier.fillMaxSize().weight(0.2f),imageVector = Icons.Default.Notifications,contentDescription = null)
            }

        }

        if (searchQuery.value.isNotEmpty() && !searchProductState.value.isData.isNullOrEmpty()){
            Log.d("searchProduct", "homeScreen: ${searchProductState.value.isData}")

            LazyVerticalGrid(
                modifier = Modifier.fillMaxWidth().weight(1f),
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                contentPadding = PaddingValues(16.dp)

            ) {
                items(searchProductState.value.isData ?: emptyList()){
                    productHomeShowCard(
                        imageUrl = it.imageUrl,
                        productTitle = it.productTitle,
                        productFinalPrice = it.finalPrice,
                        productPrice = it.price,
                        wenderName = it.wenderName,
                        onClick = {
                            val id = it.productId
                            if (!id.isNullOrEmpty()) {
                                navcontroller.navigate(Routes.productDetailedScreen(productId = id))
                            } else {
                                Log.e(id, "Product ID is null or empty")
                                Log.e("NavigationError", "Product ID is null or empty")
                            }
                        }
                    )
                }

            }



        }else{

            Spacer(modifier = Modifier.height(15.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Category",
                    fontSize = 19.5.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                )

                Text(
                    text = "See more",
                    fontSize = 15.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0XFFF68B8B),
                    modifier = Modifier.clickable{
                        navcontroller.navigate(Routes.seeAllCategoryScreen)
                    }
                )
            }
            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                items(getAllCategoryData) {
                    categoryCard(
                        imageUrl = it.imageUrl,
                        categoryName = it.name,
                        navController = navcontroller
                    )

                }
            }
            Image(
                modifier = Modifier.fillMaxWidth().height(198.17.dp).background(Color.Yellow),
                painter = painterResource(id = R.drawable.bannger_image_demo),
                contentScale = ContentScale.Crop,
                contentDescription = "bannerImage"
            )

            Spacer(modifier = Modifier.height(22.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Flash Sale",
                    fontSize = 19.5.sp,
                    textAlign = TextAlign.Start,
                    fontFamily = FontFamily(Font(R.font.montserrat_bold))
                )

                Text(
                    text = "See more",
                    fontSize = 15.sp,
                    textAlign = TextAlign.End,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0XFFF68B8B),
                    modifier = Modifier.clickable{
                        navcontroller.navigate(Routes.seemoreProduct)
                    }
                )
            }
            Spacer(modifier = Modifier.height(13.dp))

            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(getAllProductData) {
                    Log.d("productId", it.productId)

                    productHomeShowCard(
                        imageUrl = it.imageUrl,
                        productTitle = it.productTitle,
                        productFinalPrice = it.finalPrice,
                        productPrice = it.price,
                        wenderName = it.wenderName,
                        onClick = {
                            val id = it.productId
                            if (!id.isNullOrEmpty()) {
                                navcontroller.navigate(Routes.productDetailedScreen(productId = id))
                            } else {
                                Log.e(id, "Product ID is null or empty")
                                Log.e("NavigationError", "Product ID is null or empty")
                            }
                        }
                    )
                }

            }


        }
    }


}

