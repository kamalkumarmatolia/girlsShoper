package com.example.girlsshoper.presentation.components.card

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.girlsshoper.presentation.components.buttonStyle.RadioButtonWithText
import com.example.girlsshoper.ui.theme.GrayColor
import com.example.girlsshoper.ui.theme.MainColor

@Composable
fun ShippingMethodColumn(
    radioFreeSelected: Boolean,
    radioCashSelected: Boolean,
    radioFreeButtonOnClick: () -> Unit,
    radioCashButtonOnClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                BorderStroke(
                    width = 1.dp,
                    color = MainColor
                ), shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RadioButtonWithText(
            radioSelected = radioFreeSelected,
            textMessage = "Standard FREE delivery over Rs:4500",
            charge = "Free",
            onClick = {
                radioFreeButtonOnClick()
            }
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = GrayColor
        )
        RadioButtonWithText(
            radioSelected = radioCashSelected,
            textMessage = "Cash on delivery over Rs:4500 (Free Delivery, COD processing fee only)",
            charge = "100",
            onClick = {
                radioCashButtonOnClick()
            }
        )
    }
}

