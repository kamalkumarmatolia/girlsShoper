package com.example.girlsshoper.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview



@Composable
fun circularBox(
    size : Int = 400,
    x_axis : Float,
    y_axis : Float
) {

        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = Color(0xFFF68B8B),
                radius = size.toFloat(),
                center = Offset(x_axis, y = y_axis)
            )
        }


}