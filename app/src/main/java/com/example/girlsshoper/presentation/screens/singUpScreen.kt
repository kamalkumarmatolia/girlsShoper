package com.example.girlsshoper.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.components.texture.MultiColorTextExample
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.presentation.navigation.Routes
import com.example.girlsshoper.presentation.screens.dialogBox.dialogRegisterDone
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.MainColor


@Composable
fun singUpScreenUi(
    vIewModel: myVIewModel = hiltViewModel(),
    navcontroller: NavHostController
) {

    val registerUserState = vIewModel.registerUserState.collectAsState()
    val context = LocalContext.current

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        dialogRegisterDone(onDismiss = { showDialog = false })
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ){
        circularBox(
            size = 400,
            x_axis = 1050f,
            y_axis = 45f
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 34.dp)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "Singup",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(18.dp))
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
                value = email,
                onValueChange = { email = it },
                label = "email"
            )
            Spacer(modifier = Modifier.height(10.dp))
            outlineTextFieldComponent(
                value = password,
                onValueChange = { password = it },
                label = "Password"
            )
            Spacer(modifier = Modifier.height(10.dp))
            outlineTextFieldComponent(
                value = phoneNumber,
                onValueChange = { phoneNumber = it },
                label = "Phone Number"
            )

            Spacer(modifier = Modifier.height(22.dp))
            Button(
                shape = RectangleShape,
                modifier = Modifier.fillMaxWidth().height(47.dp).clip(RoundedCornerShape(12.dp)),
                colors = ButtonDefaults.buttonColors(MainColor),
                onClick = {
                    vIewModel.registerUserVModel(
                        userData = userModel(
                            firstName = firstName,
                            lastName = lastName,
                            email = email,
                            password = password,
                            phoneNumber = phoneNumber,
                            adderss = address
                        )
                    )
                }
            ) {
                Text(
                    text = "Register",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = DarkGrayColor
                )

            }

            Spacer(modifier = Modifier.height(25.dp))
            MultiColorTextExample(
                firstText = "Don't have an account? ",
                secondText = "Sing Up",
                firstColor = DarkGrayColor,
                secondColor = MainColor,
                onClick = {
                    navcontroller.navigate(Routes.loginScreen)
                }
            )

        }

        circularBox(
            size = 200,
            x_axis = 45f,
            y_axis =2200f
        )

    }


    when{
        registerUserState.value.isLoading -> {
            CircularProgressIndicator()
        }
        registerUserState.value.isData != null -> {
            showDialog == true
            navcontroller.navigate(Routes.loginScreen)
             Toast.makeText(context, registerUserState.value.isData, Toast.LENGTH_SHORT).show()
            vIewModel.resetRegisterUserState()



        }
        registerUserState.value.isError != null -> {
            Toast.makeText(context, registerUserState.value.isError, Toast.LENGTH_SHORT).show()

        }

    }


}