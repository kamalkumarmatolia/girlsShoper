package com.example.girlsshoper.presentation.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade


@Composable
fun productHomeShowCard(
    imageUrl : String ,
    productTitle : String = "One Shoulder Linen Dress",
    wenderName : String = "GF1025",
    productFinalPrice : String = "5740",
    productPrice : String = "7180",
    productDiscount : String = "20%",
    onClick : () -> Unit
) {

    val context = LocalContext.current

    Column(
        modifier = Modifier.clickable{
            onClick()
        }
    ) {
        //images
        Card(
            modifier = Modifier
                .width(115.dp)
                .height(150.dp),
            shape = RoundedCornerShape(12.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = ImageRequest.Builder(context)
                    .data(imageUrl)
                    .crossfade(true)
                    .memoryCachePolicy(CachePolicy.ENABLED)
                    .networkCachePolicy(CachePolicy.ENABLED)
                    .build(),
                contentScale = ContentScale.Crop,
                contentDescription = null
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        //productDetails
        Card(
            modifier = Modifier
                .height(122.dp)
                .width(115.dp)
                .align(Alignment.Start),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(0.5.dp, Color.LightGray),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().background(Color.White).padding(start = 9.dp, top = 8.dp)
            ) {
                Text(
                    text = productTitle,
                    fontSize = 13.sp,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.SansSerif,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 14.sp,
                    color = Color.DarkGray
                )


                Text(
                    text = wenderName,
                    fontSize = 11.sp,
                    color = Color.DarkGray
                )

                Text(
                    text = "Rs: ${productFinalPrice} ",
                    fontSize = 17.5.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFFF68B8B)
                )
                Spacer(modifier = Modifier.height(2.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Rs :${productPrice} ",
                        style = TextStyle(
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.LineThrough,
                            color = Color(0xFF333333)
                        )
                    )

                    // Discount Text
                    Text(
                        text = productDiscount,
                        style = TextStyle(
                            fontSize = 13.5.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFE57373)
                        )
                    )
                }

            }

        }

    }

}