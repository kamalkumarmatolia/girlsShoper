package com.example.girlsshoper.presentation.screens

import android.util.Log
import androidx.annotation.Size
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.girlsshoper.R
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.toColorLong
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.girlsshoper.domain.module.productColorLists
import com.example.girlsshoper.presentation.components.buttonStyle.coustomButton
import com.example.girlsshoper.presentation.components.card.productSIzeCard
import com.example.girlsshoper.presentation.components.card.ratingCard
import com.example.girlsshoper.presentation.components.quntitySelector
import com.example.girlsshoper.presentation.navigation.Routes
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.presentation.viewModel.toColor
import com.example.girlsshoper.ui.theme.BlackColor
import com.example.girlsshoper.ui.theme.GrayColor
import com.example.girlsshoper.ui.theme.MainColor
import com.example.girlsshoper.ui.theme.WhiteColor
import kotlin.collections.mutableListOf


@Composable
fun productDetailedScreenUi(
    vIewModel: myVIewModel = hiltViewModel(),
    productId : String,
    navcontroller: NavHostController
) {
    val context = LocalContext.current
    val getSpacificProductState = vIewModel.getProductByIDState.collectAsState()
    val pdrdata = getSpacificProductState.value.isData
    var quantity by remember { mutableStateOf(1) }
    var sizeSelected by rememberSaveable {
        mutableStateOf("")
    }
    var colorSelected by  remember {
        mutableStateOf<productColorLists?>(null)
    }

    LaunchedEffect(key1 = Unit) {
        vIewModel.getProductByIDVModel(productId = productId)
    }


    when{
        getSpacificProductState.value.isLoading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){ CircularProgressIndicator() }
        }
        getSpacificProductState.value.isData != null -> {
            Box(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {

                Box(modifier = Modifier.fillMaxWidth().height(500.dp).align(Alignment.TopCenter)){
                    AsyncImage(
                        modifier = Modifier.fillMaxSize(),
                        model = ImageRequest.Builder(context)
                            .data(pdrdata?.imageUrl)
                            .crossfade(true)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .networkCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null
                    )

                    IconButton(
                        onClick = {
                            navcontroller.navigate(Routes.homeScreen)
                        },
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 27.dp).size(28.dp).align(Alignment.TopStart),
                        colors = IconButtonDefaults.iconButtonColors(containerColor = Color.White),

                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBackIos,
                            contentDescription = null,
                            modifier = Modifier
                                .size(22.dp)
                                .align(Alignment.CenterEnd)
                                .background(Color.White)
                        )
                    }


                }
                Column(modifier = Modifier.fillMaxWidth().padding(top = 390.dp).align(Alignment.BottomCenter)){
                    Column(
                        modifier = Modifier.fillMaxSize().padding(horizontal = 20.dp)
                    ) {
                        Text(
                            text = pdrdata?.productTitle ?: "",
                            fontSize = 23.sp,
                            maxLines = 2,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.SansSerif,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        ratingCard(
                            rating = pdrdata?.productRating ?: 0f
                        )

                        Text(
                            text = "Rs: ${pdrdata?.price} ",
                            fontSize = 26.sp,
                            color = Color.LightGray,
                            fontFamily = FontFamily(Font(R.font.montserrat_bold))

                        )


                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Size",
                                style = TextStyle(
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.End,
                                    color = BlackColor,
                                    fontFamily = FontFamily(Font(R.font.montserrat_bold))
                                )
                            )
                            Text(
                                text = "see more",
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.End,
                                    color = MainColor,
                                    fontFamily = FontFamily(Font(R.font.montserrat_bold))
                                )
                            )
                        }
                        
                        Spacer(modifier  = Modifier.height(5.dp))

                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                            LazyRow(
                                modifier = Modifier.fillMaxWidth().weight(0.7f),
                                horizontalArrangement = Arrangement.spacedBy(12.dp)
                            ) {
                                items(pdrdata?.productSize!!) {

                                    productSIzeCard(
                                        text = it,
                                        isSelected = it == sizeSelected  ,
                                        onClick = {
                                            sizeSelected = it
                                        }
                                    )
                                }
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth().weight(0.4f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                quntitySelector(
                                    quantity = quantity,
                                    onQuantityChange = {quantity = it},
                                    modifier = Modifier.weight(0.4f)
                                )

                            }


                        }
                        Spacer(modifier = Modifier.height(11.dp))

                        //color
                        Text(
                            text = "Color",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W700,
                                textAlign = TextAlign.Start,
                                color = BlackColor,
                                fontFamily = FontFamily(Font(R.font.montserrat_bold))
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        LazyRow(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            items(pdrdata?.productColor!!){
                                colorSelector(
                                    colorItem = it,
                                    colorSelected = colorSelected == it,
                                    onClick = {
                                        colorSelected = it
                                        Log.d("colorSelectCLick", colorSelected.toString())
                                    }

                                )


                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))
                        //Spacification
                        Text(
                            text = "Spacification",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = FontWeight.W700,
                                textAlign = TextAlign.Start,
                                color = BlackColor,
                                fontFamily = FontFamily(Font(R.font.montserrat_bold))
                            )
                        )


                        Column(
                            modifier = Modifier.fillMaxWidth().padding(top = 5.dp)

                        ) {
                            Text(
                                text = "name ${pdrdata?.productTitle}",
                                fontSize = 15.sp,

                            )
                            Text(
                                text = pdrdata?.description ?: "",
                                fontSize = 15.sp,
                                maxLines = 5,
                                overflow = TextOverflow.Clip,
                                modifier = Modifier.fillMaxWidth()
                            )

                            //addBuy
                            Spacer(modifier = Modifier.height(16.dp))

                            coustomButton(
                                text = "Buy now",
                                onClick = {
                                    navcontroller.navigate(Routes.shipingScreenScreen(
                                        productID = productId,
                                        productQuintity = quantity,
                                        productColorCode = colorSelected?.colorCode ?: "",
                                        productColorName = colorSelected?.colorName ?: "",
                                        productSize = sizeSelected
                                    ))
                                }
                            )
                            Spacer(modifier = Modifier.height(6.dp))
                            coustomButton(
                                text = "Add to cart",
                                onClick = {},
                                containerColor = GrayColor
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {

                                if (getSpacificProductState.value.isData!= null) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        tint = MainColor
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.FavoriteBorder,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp),
                                        tint = MainColor
                                    )
                                }
                                Spacer(Modifier.width(4.dp))
                                Text(
                                    text = "Add to wishlist",
                                    fontSize = 15.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = MainColor,
                                    modifier = Modifier.clickable{

                                    },
                                )

                            }

                        }

                    }

                }

            }

        }
        getSpacificProductState.value.isError != null -> {
            Text(text = getSpacificProductState.value.isError ?: "error")
        }
    }

}

@Composable
fun colorSelector(
    colorItem: productColorLists,
    colorSelected : Boolean,
    onClick: () -> Unit
){

    Box(
        modifier = Modifier
            .size(38.dp)
            .background(Color(android.graphics.Color.parseColor(colorItem.colorCode)), RoundedCornerShape(6.dp))
            .border(
                width = 2.dp,
                color = if (colorSelected) Color(0xFFF68B8B) else  WhiteColor,
                shape = RoundedCornerShape(6.dp)
            )
            .clickable{
                onClick()
            }
    )


}

