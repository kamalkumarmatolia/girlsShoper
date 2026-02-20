package com.example.girlsshoper.presentation.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.girlsshoper.R
import com.example.girlsshoper.domain.module.productColorLists
import com.example.girlsshoper.presentation.viewModel.toColor

import com.example.girlsshoper.ui.theme.DarkGrayColor
import okio.ByteString.Companion.decodeHex

@Composable
fun eachProductViewCard(
    imageUrl: String,
    productTitle : String,
    wenderName : String,
    productSize : List<Any>,
    productColor : List<productColorLists> = emptyList()
) {
    val context = LocalContext.current

    Row(
        modifier = Modifier.width(187.dp)
    ) {

        Card(
            modifier = Modifier
                .width(87.dp)
                .height(120.dp),
            shape = RoundedCornerShape(8.dp),
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

        Spacer(Modifier.width(11.dp))

        Column(
            modifier = Modifier.fillMaxHeight().width(100.dp).padding(top = 7.dp)
        ) {
            Text(
                text = productTitle,
                fontSize = 11.sp,
                maxLines = 2,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.montserrat_bold)),
                overflow = TextOverflow.Ellipsis,
                lineHeight = 14.sp,
                color = Color.DarkGray
            )
            Spacer(modifier = Modifier.height(5.dp))



            Text(
                text = wenderName,
                fontSize = 11.sp,
                lineHeight = 13.5.sp,
                color = DarkGrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_regular))
            )

            LazyRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                item {
                    Text(
                        text = "Size: ",
                        fontSize = 11.sp
                    )
                }
                items(productSize){
                    Text(
                        text = "${it}, ",
                        fontSize = 11.sp
                    )
                }

            }
            LazyRow(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(2.dp)
            ) {

                item {
                    Text(
                        text = "Color: ",
                        style = TextStyle(
                            fontSize = 11.sp,
                            color = Color(0xFF333333)
                        )
                    )
                }

                items(productColor){
                    Box(
                        modifier = Modifier
                            .size(14.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(it.colorCode.toColor())
                    )

                }


            }

        }

    }

    
}