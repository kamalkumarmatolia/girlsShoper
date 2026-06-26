package com.example.girlsshoper.presentation.components.card

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.girlsshoper.domain.module.productColorLists

@Composable
fun shopingCartCard(
    imageUrl : String,
    productTitle :String,
    productSize : String,
    productColor : String,
    productFinalPrice : String,
    productQUntity : Int
) {


    Row(
        modifier = Modifier.fillMaxWidth().height(120.dp)
    ){
        eachProductViewCard(
            imageUrl = imageUrl,
            productTitle = productTitle,
            productcolorCodeOnly = productColor,
            singleProductSize = productSize,
            checkerScreen = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Rs:\n${productFinalPrice} ",
            modifier = Modifier.padding(top = 7.dp),
            lineHeight = 13.5.sp,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = productQUntity.toString(),
            modifier = Modifier.padding(top = 7.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(11.dp))
        Text(
            text = "${productFinalPrice.toInt() * productQUntity} ",
            modifier = Modifier.padding(top = 7.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(5.dp))
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Delete",
            tint = Color.Black,
            modifier = Modifier.padding(top =9.dp)
        )

    }


}