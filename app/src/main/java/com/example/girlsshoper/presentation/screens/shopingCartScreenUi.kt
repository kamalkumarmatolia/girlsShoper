package com.example.girlsshoper.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import com.example.girlsshoper.R
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.presentation.components.buttonStyle.coustomButton
import com.example.girlsshoper.presentation.components.card.shopingCartCard
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.GrayColor
import com.example.girlsshoper.ui.theme.MainColor

@Composable
fun shopingCartScreenUi(
    vIewModel: myVIewModel = hiltViewModel(),
    navcontroller: NavHostController
) {

    val getAllCartproductState = vIewModel.getAllProductState.collectAsState()
    val data = getAllCartproductState.value.isData ?: emptyList()

    LaunchedEffect(Unit) {
        vIewModel.getAllProductVModel()
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        circularBox(
            size = 390,
            x_axis = 1050f,
            y_axis = 45f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp)
                .padding(top = 63.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Shopping Cart",
                fontSize = 19.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start)
            )
            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "< Continue Shopping",
                fontSize = 12.sp,
                color= DarkGrayColor,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.fillMaxWidth(),
                lineHeight = 16.sp
            )
            Spacer(modifier = Modifier.height(7.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Spacer(modifier= Modifier.width(17.dp))
                Text(
                    text = "Items",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= DarkGrayColor,
                )
                Spacer(modifier= Modifier.width(135.dp))

                Text(
                    text = "Price",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= DarkGrayColor
                )
                Spacer(modifier= Modifier.width(11.dp))
                Text(
                    text = "QTY",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= DarkGrayColor
                )
                Spacer(modifier= Modifier.width(8.dp))
                Text(
                    text = "Total",
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color= DarkGrayColor
                )

            }

            Spacer(modifier = Modifier.height(8.dp))

            when{
                getAllCartproductState.value.isLoading -> {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        color = MainColor
                    )
                }
                getAllCartproductState.value.isData != null -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth().wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(7.dp)
                    ) {
                        items(data){
                            shopingCartCard(
                                imageUrl = it.imageUrl,
                                productTitle = it.productTitle,
                                wenderName = it.wenderName,
                                productSize = it.productSize,
                                productColor = it.productColor,
                                productFinalPrice = it.finalPrice,
                                productQUntity = it.abalibilibalUnits
                            )

                        }
                        item {
                            Spacer(modifier = Modifier.height(10.dp))
                            HorizontalDivider(
                                color = GrayColor,
                                thickness = 1.dp,
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                modifier = Modifier.fillMaxWidth(),
                                text = "Sub Total 8000",
                                fontSize = 15.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.End,
                                fontFamily = FontFamily(Font(R.font.montserrat_bold))
                            )
                            Spacer(modifier = Modifier.height(13.dp))
                            coustomButton(
                                text = "Checkout",
                                onClick = {
                                    navcontroller.navigate("shipingScreen")
                                },
                                containerColor = GrayColor
                            )
                            Spacer(modifier = Modifier.height(10.dp))

                        }
                    }
                }
                getAllCartproductState.value.isError != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ){
                        Text(text = "No Cart Added")
                    }
                    Toast.makeText(LocalContext.current, getAllCartproductState.value.isError, Toast.LENGTH_SHORT).show()
                }

            }

        }

    }



}