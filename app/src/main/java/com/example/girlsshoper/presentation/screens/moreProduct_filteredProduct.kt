 package com.example.girlsshoper.presentation.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.presentation.components.card.seemoreProductCard
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.MainColor

@Composable
fun moreProduct_filteredProduct(
    vIewModel: myVIewModel = hiltViewModel(),
    navcontroller: NavHostController,
    refranceName : String? = null
) {

    val getAllproductState by vIewModel.getAllProductState.collectAsState()
    val getAllproduct = getAllproductState.isData ?: emptyList()
    var searchValued by remember { mutableStateOf("") }

    val getProductByCategorystate by vIewModel.getProductByCategorystate.collectAsState()
    val searchState by vIewModel.searchProductState.collectAsState()


    val currentState = if (refranceName != null) {
        getProductByCategorystate
    } else {
        getAllproductState
    }

    val currentProduct = when {

        searchValued.isNotEmpty() && refranceName != null -> {
            getProductByCategorystate.isData
                ?.filter {
                    it.productTitle.contains(searchValued, ignoreCase = true)
                } ?: emptyList()
        }
        searchValued.isNotEmpty() -> { searchState.isData ?: emptyList() }
        refranceName != null -> { getProductByCategorystate.isData ?: emptyList() }
        else -> { getAllproductState.isData ?: emptyList() }
    }

    val listofProducts by remember(searchValued, getAllproduct, getProductByCategorystate) {
        derivedStateOf {
            val baseList = if (refranceName != null){
                getProductByCategorystate.isData ?: emptyList()
            }else {
                getAllproduct
            }
            if (searchValued.isNotEmpty()){
                baseList.filter {
                    it.productTitle.contains(searchValued, ignoreCase = true)
                }
            }else {
                baseList
            }

        }
    }

    LaunchedEffect(refranceName) {
        if (refranceName != null){
            vIewModel.getProductByCategoryVModel(categoryName = refranceName)
        }else {
            vIewModel.getAllProductVModel()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        circularBox(
            size = 400,
            x_axis = 1050f,
            y_axis = 45f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 21.dp)
                .padding(top = 63.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = null,
                    modifier = Modifier.clickable{
                        navcontroller.navigateUp()
                    }
                )
                Text(
                    text = refranceName ?: "see more",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                modifier = Modifier.fillMaxWidth().padding(start = 6.dp),
                verticalAlignment = Alignment.CenterVertically,

            ) {
                Text(
                    text = "Items",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= DarkGrayColor,
                )
                Spacer(modifier= Modifier.width(163.dp))

                Text(
                    text = "Price",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= DarkGrayColor
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            outlineTextFieldComponent(
                value = searchValued,
                onValueChange = {
                    searchValued = it
                    vIewModel.onSearchQueryChange(it)
                },
                label = "sarch"
            )
            Spacer(modifier = Modifier.height(15.dp))

            when{
                currentState.isLoading  -> {
                    Log.d("productLoading","running Loading")
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MainColor
                    )
                }
                currentState.isData != null -> {
                    Log.d("productLoading","running DATA")
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(currentProduct ?: emptyList()){

                            seemoreProductCard(
                                imageUrl = it.imageUrl,
                                productTitle = it.productTitle,
                                wenderName = it.wenderName,
                                productSize = it.productSize,
                                productColor = it.productColor,
                                productFinalPrice = it.finalPrice,
                                onClick = {

                                }
                            )

                        }
                    }
                }
                currentState.isError != null -> {
                    Log.d("productLoading","running Error")
                    Toast.makeText(LocalContext.current, getAllproductState.isError, Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

}