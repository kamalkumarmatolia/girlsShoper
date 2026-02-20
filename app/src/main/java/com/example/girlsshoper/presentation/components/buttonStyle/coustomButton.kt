package com.example.girlsshoper.presentation.components.buttonStyle

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.girlsshoper.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.girlsshoper.ui.theme.MainColor
import com.example.girlsshoper.ui.theme.WhiteColor

@Composable
fun coustomButton(
    onClick: () -> Unit,
    text: String,
    containerColor: Color = MainColor,
    contentColor: Color = WhiteColor,
    borderStroke: BorderStroke = BorderStroke(0.dp, Color.Transparent)
) {

    Button(
        onClick = {
            onClick()
        },
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            contentColor = contentColor,
            containerColor = containerColor
        ),
        border = borderStroke

    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.W600,
            fontFamily = FontFamily(Font(R.font.montserrat_regular))
        )
    }
}

