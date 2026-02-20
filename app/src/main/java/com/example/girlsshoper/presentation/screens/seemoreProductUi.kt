 package com.example.girlsshoper.presentation.screens

import android.widget.Toast
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.presentation.components.card.seemoreProductCard
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.components.texture.MultiColorTextExample
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.MainColor
import com.google.android.play.integrity.internal.n


@Composable
fun seemoreProductUi(
    vIewModel: myVIewModel = hiltViewModel(),
    navcontroller: NavHostController
) {

    val getAllproductState = vIewModel.getAllProductState.collectAsState()
    val data = getAllproductState.value.isData ?: emptyList()
    var email by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        vIewModel.getAllProductVModel()
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

            Text(
                text = "See More",
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "< See Your Favourite\n"+"               one",
                fontSize = 15.sp,
                color= DarkGrayColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 16.sp
            )

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
                value = email,
                onValueChange = { email = it },
                label = "sarch"
            )
            Spacer(modifier = Modifier.height(15.dp))

            when{
                getAllproductState.value.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MainColor
                    )
                }
                getAllproductState.value.isData != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(5.dp)
                    ) {
                        items(data){

                            seemoreProductCard(
                                imageUrl = it.imageUrl,
                                productTitle = it.productTitle,
                                wenderName = it.wenderName,
                                productSize = it.productSize,
                                productColor = it.productColor,
                                productFinalPrice = it.finalPrice
                            )

                        }
                    }
                }
                getAllproductState.value.isError != null -> {
                    Toast.makeText(LocalContext.current, getAllproductState.value.isError, Toast.LENGTH_SHORT).show()
                }

            }

        }

    }

}