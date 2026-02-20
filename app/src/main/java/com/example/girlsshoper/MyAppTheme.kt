package com.example.girlsshoper

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.girlsshoper.ui.theme.WhiteColor
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun MyAppTheme(content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()

    // Change the status bar color here
    LaunchedEffect(Unit) {
        systemUiController.setStatusBarColor(
            color = WhiteColor, // Your desired color
            darkIcons = true // or false based on your needs
        )
    }

    MaterialTheme {
        // Your existing theme setup
        content()
    }
}