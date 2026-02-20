package com.example.girlsshoper.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.girlsshoper.presentation.components.buttonStyle.coustomButton
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.components.texture.MultiColorTextExample
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.presentation.navigation.Routes
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.MainColor


@Composable
fun loginScreenui(
    vIewModel: myVIewModel = hiltViewModel(),
    navcontroller: NavHostController
) {

    val loginWithEmailPassState = vIewModel.loginWithEmailPassState.collectAsState()
    val context = LocalContext.current

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
                .padding(top = 104.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }

            Text(
                text = "Login",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(26.dp))
            outlineTextFieldComponent(
                value = email,
                onValueChange = { email = it },
                label = "email"
            )

            Spacer(modifier = Modifier.height(24.dp))

            outlineTextFieldComponent(
                value = password,
                onValueChange = { password = it },
                label = "Password"
            )
            Spacer(modifier = Modifier.height(11.dp))
            Text(
                text = "Forgot Password",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.align(Alignment.End)
            )

            Spacer(modifier = Modifier.height(34.dp))
            coustomButton(
                text = "Login",
                onClick = {
                    vIewModel.loginWithEmailAndPassVModel(email,password)
                }
            )

            Spacer(modifier = Modifier.height(27.dp))
            MultiColorTextExample(
                firstText = "Don't have an account? ",
                secondText = "Sing Up",
                firstColor = DarkGrayColor,
                secondColor = MainColor,
                onClick = {
                    navcontroller.navigate(Routes.singupScreen)
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
        loginWithEmailPassState.value.isLoading -> {
            CircularProgressIndicator()
        }
        loginWithEmailPassState.value.isData != null -> {
            Toast.makeText(context, loginWithEmailPassState.value.isData, Toast.LENGTH_SHORT).show()
            navcontroller.navigate(Routes.homeScreen)
        }
        loginWithEmailPassState.value.isError != null -> {
            Toast.makeText(context, loginWithEmailPassState.value.isError, Toast.LENGTH_SHORT).show()
        }

    }

}