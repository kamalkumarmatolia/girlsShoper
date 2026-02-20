package com.example.girlsshoper.presentation.screens

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import com.example.girlsshoper.R
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.girlsshoper.MainActivity
import com.example.girlsshoper.presentation.components.buttonStyle.coustomButton
import com.example.girlsshoper.presentation.components.card.ShippingMethodColumn
import com.example.girlsshoper.presentation.components.card.shipingProductDetailCard
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.ui.theme.BlackColor
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.GrayColor
import com.example.girlsshoper.ui.theme.MainColor
import com.example.girlsshoper.ui.theme.WhiteColor

@Composable
fun shipingScreenUi(
    navController: NavHostController,
    productID : String,
    productQuintity : Int,
    productColorCode : String,
    productColorName : String,
    productSize : String
) {

    val scrollState = rememberScrollState()
    var userEmail by remember { mutableStateOf("technokamal4@gmail.com") }
    var country by remember { mutableStateOf("India") }
    var city by remember { mutableStateOf("city") }
    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var pinCode by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("969696969") }
    var address by remember { mutableStateOf("") }
    var checkInfoSave by remember { mutableStateOf(false) }
    var radioFreeSelected by remember { mutableStateOf(false) }
    var radioCashSelected by remember { mutableStateOf(false) }
    val context = LocalContext.current
    val activity = context as? MainActivity



    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 17.dp)
            .padding(
                top = 15.dp,
                bottom = 15.dp
            )
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Shiping",
            fontSize = 22.sp,
            fontWeight = FontWeight.W600,
            color = BlackColor,
            fontFamily = FontFamily(Font(R.font.montserrat_bold)),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{
                    navController.navigateUp()
                }
            ,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIosNew,
                contentDescription = "arrowBack",
                modifier = Modifier.size(20.dp),
                tint = GrayColor
            )
            Text(
                text = "Return to cart",
                fontSize = 13.sp,
                fontWeight = FontWeight.W400,
                color = GrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )

        }
        Spacer(modifier = Modifier.height(4.dp))
        shipingProductDetailCard(
            productQuintity = productQuintity,
            productColorCode = productColorCode,
            productSize = productSize
        )
        Spacer(modifier = Modifier.height(5.dp))
        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            thickness = 1.dp,
            color = BlackColor
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Contact Information",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = BlackColor,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        outlineTextFieldComponent(
            value = userEmail,
            onValueChange = { userEmail = it }
        )
        outlineTextFieldComponent(
            value = phoneNumber,
            onValueChange = { phoneNumber = it }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Shipping Address",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = BlackColor,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(10.dp))
        outlineTextFieldComponent(
            value = country,
            onValueChange = { country = it },
            label = "country"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            outlineTextFieldComponent(
                value = firstName,
                onValueChange = { firstName = it },
                label = "First Name",
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            outlineTextFieldComponent(
                value = lastName,
                onValueChange = { lastName = it },
                label = "Last Name",
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        outlineTextFieldComponent(
            value = address,
            onValueChange = { address = it },
            label = "email"
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            outlineTextFieldComponent(
                value = city,
                onValueChange = { city = it },
                label = "city",
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
            outlineTextFieldComponent(
                value = pinCode,
                onValueChange = { pinCode = it },
                label = "pinCode",
                modifier = Modifier.fillMaxWidth().weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {

            Checkbox(
                checked = checkInfoSave,
                onCheckedChange = { checkInfoSave = it },
                modifier = Modifier
                    .size(12.dp),
                colors = CheckboxDefaults.colors(
                    checkedColor = MainColor,
                    checkmarkColor = WhiteColor
                )
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = "Save this information for next time",
                fontSize = 12.5.sp,
                fontWeight = FontWeight.W400,
                color = GrayColor,
                fontFamily = FontFamily(Font(R.font.montserrat_bold))
            )

        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Shipping Method",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = BlackColor,
            modifier = Modifier.fillMaxWidth(),
            fontFamily = FontFamily(Font(R.font.montserrat_bold))
        )
        Spacer(modifier = Modifier.height(10.dp))
        ShippingMethodColumn(
            radioFreeSelected = radioFreeSelected,
            radioCashSelected = radioCashSelected,
            radioFreeButtonOnClick = {
                radioFreeSelected =! radioFreeSelected
                if (radioFreeSelected) radioCashSelected = false
            },
            radioCashButtonOnClick = {
                radioCashSelected =! radioCashSelected
                if (radioCashSelected) radioFreeSelected = false
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        coustomButton(
            text = "Continue to Shipping",
            onClick = {
                activity?.startPayment(
                    name = "product",
                    payableAmount = 100
                )
            },
            containerColor = GrayColor,
            contentColor = WhiteColor
        )

    }
}