package com.example.girlsshoper.presentation.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.presentation.components.card.categoryCard
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.viewModel.myVIewModel

@Composable
fun seeAllCategoryUi(
    vIewModel: myVIewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(key1 = Unit) {
        vIewModel.getAllCategoryVModel()
    }

    val getAllCategoryState = vIewModel.getAllCategoryState.collectAsState()
    val allCategoryData   = getAllCategoryState.value.isData ?: emptyList()
    val context = LocalContext.current




    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        circularBox(
            size = 400,
            x_axis = 1050f,
            y_axis = 45f
        )

        when {
            getAllCategoryState.value.isLoading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
                    CircularProgressIndicator()
                }
            }
            getAllCategoryState.value.isData != null -> {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .padding(top = 63.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "back",
                            modifier = Modifier.clickable { navController.navigateUp() }.size(23.dp)
                        )
                        Text(
                            text = " See All Category",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.SemiBold,

                        )

                    }


                    Spacer(Modifier.height(25.dp))

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(5.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        items(allCategoryData) {
                            categoryCard(
                                imageUrl = it.imageUrl,
                                categoryName = it.name,
                                navController = navController,
                                iconSize = 85
                            )

                        }

                    }
                }
            }
            getAllCategoryState.value.isError != null -> {
                Toast.makeText(context, getAllCategoryState.value.isError, Toast.LENGTH_SHORT).show()
            }
        }

    }

}