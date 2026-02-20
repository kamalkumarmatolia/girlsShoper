package com.example.girlsshoper.presentation.components.card

import com.example.girlsshoper.R
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.girlsshoper.ui.theme.BlackColor
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.GrayColor
import com.example.girlsshoper.ui.theme.MainColor


@Composable
fun shipingProductDetailCard(
    imageUrl : String = "https://i.pinimg.com/236x/bc/b0/22/bcb0224d9dbedab522340d4e50c3360d.jpg",
    productQuintity : Int,
    productColorCode : String,
    productSize : String
) {

    val context = LocalContext.current

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.Top,
            horizontalArrangement = Arrangement.Start
        ) {
            Card(
                modifier = Modifier
                    .width(62.dp)
                    .height(80.dp),
                elevation = CardDefaults.cardElevation(4.dp)
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = ImageRequest.Builder(context)
                        .data(imageUrl)
                        .size(height = 100, width = 87)
                        .crossfade(true)
                        .memoryCachePolicy(CachePolicy.ENABLED)
                        .networkCachePolicy(CachePolicy.ENABLED)
                        .build(),
                    contentScale = ContentScale.Crop,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.width(5.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "One Shoulder Linen Dress asas akakak",
                        fontSize = 11.5.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkGrayColor,
                        maxLines = 2,
                        lineHeight = 14.5.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                        modifier = Modifier.fillMaxWidth().weight(1f)
                    )
                    Text(
                        text = "5000",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkGrayColor,
                        textAlign = TextAlign.End,
                        modifier = Modifier.fillMaxWidth().weight(0.3f),
                        fontFamily = FontFamily(Font(R.font.montserrat_bold))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "GF3331",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.W700,
                        color = DarkGrayColor,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold))
                    )
                    Text(
                        text = "x${productQuintity}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        color = GrayColor,
                        fontFamily = FontFamily(Font(R.font.montserrat_bold))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = productSize,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W700,
                        color = GrayColor,
                        lineHeight = 14.sp,
                        fontFamily = FontFamily(Font(R.font.montserrat_regular))
                    )
                    Spacer(Modifier.width(5.dp))
                    Box(
                        modifier = Modifier
                            .size(13.dp)
                            .shadow(
                                elevation = 1.dp,
                                shape = RoundedCornerShape(3.dp)
                            )
                            .background(Color(android.graphics.Color.parseColor(productColorCode)))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(7.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.6.dp,
            color = GrayColor
        )
        Spacer(modifier = Modifier.height(5.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "GF3331",
                fontSize = 11.sp,
                fontWeight = FontWeight.W700,
                color = GrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )
            Text(
                text = "Rs: 5700",
                fontSize = 11.sp,
                fontWeight = FontWeight.W700,
                color = GrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )

        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "Shipping",
                fontSize = 11.sp,
                fontWeight = FontWeight.W700,
                color = GrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )
            Text(
                text = "Free",
                fontSize = 11.sp,
                fontWeight = FontWeight.W700,
                color = GrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )

        }
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 0.6.dp,
            color = GrayColor
        )

        Spacer(modifier = Modifier.height(4.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween

        ) {
            Text(
                text = "Total",
                fontSize = 14.sp,
                fontWeight = FontWeight.W700,
                color = DarkGrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )
            Text(
                text = "Rs: 5700",
                fontSize = 14.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )

        }
        Spacer(modifier = Modifier.height(4.dp))

    }

}