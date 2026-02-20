package com.example.girlsshoper.presentation.components.texture

import androidx.compose.foundation.clickable
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import kotlin.text.append

@Composable
fun MultiColorTextExample(
    firstText : String, secondText : String,
    firstColor : Color, secondColor : Color,
    onClick : () -> Unit
) {
    Text(
        text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = firstColor, fontSize = 15.sp)) {
                append(firstText)
            }
            withStyle(style = SpanStyle(color = secondColor, fontWeight = FontWeight.SemiBold, fontSize = 15.sp)) {
                append(secondText)
            }
        },
        modifier = Modifier.clickable{
            onClick()
        }
    )
}