package com.example.girlsshoper.presentation.screens

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil3.compose.AsyncImage
import coil3.compose.SubcomposeAsyncImage
import coil3.request.CachePolicy
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.girlsshoper.domain.module.userModel
import com.example.girlsshoper.presentation.components.buttonStyle.coustomButton
import com.example.girlsshoper.presentation.components.circularBox
import com.example.girlsshoper.presentation.components.texture.outlineTextFieldComponent
import com.example.girlsshoper.presentation.viewModel.myVIewModel
import com.example.girlsshoper.ui.theme.DarkGrayColor
import com.example.girlsshoper.ui.theme.MainColor
import com.example.girlsshoper.ui.theme.WhiteColor
import com.google.firebase.auth.FirebaseAuth

@Composable
fun profileScreenUi(
    viewModel: myVIewModel = hiltViewModel(),
    navController: NavHostController,
    firebaseAuth: FirebaseAuth
) {
    val context = LocalContext.current
    val getSpacUserByIdState = viewModel.getSpacUserByIdState.collectAsState()

    var firstName by remember { mutableStateOf("") }
    var lastName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    val userImageUrl by remember { mutableStateOf("") }
    var userImageUri by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }


    LaunchedEffect(Unit) {
        viewModel.getSpacUserByIdVModel(firebaseAuth.currentUser?.uid!!)
    }

    Box(
        Modifier.fillMaxSize()

    ) {
        circularBox(
            size = 400,
            x_axis = 1050f,
            y_axis = 45f
        )
        circularBox(
            size = 200,
            x_axis = 45f,
            y_axis =2200f
        )

        when{
            getSpacUserByIdState.value.isLoading -> {
                Box(
                    modifier = Modifier.fillMaxSize().background(DarkGrayColor.copy(alpha = 0.5f)),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = MainColor)
                }
            }
            getSpacUserByIdState.value.isData != null -> {
                Log.d("userData", getSpacUserByIdState.value.isData.toString())

                LaunchedEffect(key1 = getSpacUserByIdState.value.isData) {
                    getSpacUserByIdState.value.isData.let {
                        firstName = it?.firstName ?: ""
                        lastName = it?.lastName ?: ""
                        email = it?.email ?: ""
                        phoneNumber = it?.phoneNumber ?: ""
                        userImageUri = it?.userImage ?: ""
                        address = it?.adderss ?: ""
                    }

                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 22.dp)
                        .padding(top = 47.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {

                    SubcomposeAsyncImage(
                        modifier = Modifier
                            .size(95.dp)
                            .align(Alignment.Start)
                            .shadow(
                                elevation = 1.dp,
                                shape = CircleShape
                            )
                        ,
                        model = ImageRequest.Builder(context)
                            .data(getSpacUserByIdState.value.isData?.userImage)
                            .size(95)
                            .crossfade(true)
                            .memoryCachePolicy(CachePolicy.ENABLED)
                            .networkCachePolicy(CachePolicy.ENABLED)
                            .build(),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        loading = {
                            CircularProgressIndicator(
                                color = MainColor,
                            )
                        }
                    )
                    Spacer(modifier = Modifier.height(36.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        outlineTextFieldComponent(
                            value = firstName,
                            onValueChange = { firstName = it },
                            label = "first name",
                            modifier = Modifier.weight(1f)
                        )
                        Spacer(modifier = Modifier.width(16.dp))

                        outlineTextFieldComponent(
                            value = lastName,
                            onValueChange = { lastName = it },
                            label = "last name",
                            modifier = Modifier.weight(1f)
                        )

                    }
                    Spacer(modifier = Modifier.height(9.dp))
                    outlineTextFieldComponent(
                        value = email,
                        onValueChange = { email = it },
                        label = "email",
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    outlineTextFieldComponent(
                        value = phoneNumber,
                        onValueChange = { phoneNumber = it },
                        label = "phone number",
                    )
                    Spacer(modifier = Modifier.height(9.dp))
                    outlineTextFieldComponent(
                        value = address,
                        onValueChange = { address = it },
                        label = "address",
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    coustomButton(
                        text = "Log Out",
                        onClick = {}
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    coustomButton(
                        text = "Edit Profile",
                        onClick = {},
                        containerColor = Color.Transparent,
                        contentColor = Color.Black,
                        borderStroke = BorderStroke(
                            width = 1.dp,
                            color = MainColor
                        )
                    )




                }
            }
            getSpacUserByIdState.value.isError != null -> {
                Log.d("userData", getSpacUserByIdState.value.isError.toString())
            }

        }



    }

}