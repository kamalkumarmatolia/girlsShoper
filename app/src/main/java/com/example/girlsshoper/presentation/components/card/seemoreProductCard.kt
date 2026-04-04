package com.example.girlsshoper.presentation.components.card

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.girlsshoper.R
import com.example.girlsshoper.domain.module.productColorLists
import com.example.girlsshoper.ui.theme.MainColor

//eachProductView


@Composable
fun seemoreProductCard(
    imageUrl : String,
    productTitle : String ,
    wenderName : String ,
    productSize : List<Any>,
    productColor : List<productColorLists> ,
    productFinalPrice : String,
    onClick : () -> Unit
) {

    Row(
        modifier = Modifier.fillMaxWidth()
    ){
        eachProductViewCard(
            imageUrl = imageUrl,
            productTitle = productTitle,
            wenderName = wenderName,
            productSize = productSize,
            productColor = productColor
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = "Rs: ${productFinalPrice} ",
            modifier = Modifier.padding(top = 7.dp),
            fontSize = 15.sp,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            fontWeight = FontWeight.Bold
        )

    }


}