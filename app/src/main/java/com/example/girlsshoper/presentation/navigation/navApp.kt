package com.example.girlsshoper.presentation.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HeartBroken
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person2
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.HeartBroken
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person2
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import androidx.navigation.toRoute
import com.example.girlsshoper.BottomNavigation
import com.example.girlsshoper.presentation.screens.homeScreenUi
import com.example.girlsshoper.presentation.screens.loginScreenui
import com.example.girlsshoper.presentation.screens.productDetailedScreenUi
import com.example.girlsshoper.presentation.screens.profileScreenUi
import com.example.girlsshoper.presentation.screens.seeAllCategoryUi
import com.example.girlsshoper.presentation.screens.seemoreProductUi
import com.example.girlsshoper.presentation.screens.shipingScreenUi
import com.example.girlsshoper.presentation.screens.shopingCartScreenUi
import com.example.girlsshoper.presentation.screens.singUpScreenUi
import com.example.girlsshoper.ui.theme.BlackColor
import com.example.girlsshoper.ui.theme.MainColor
import com.example.girlsshoper.ui.theme.PurpleGrey80
import com.example.girlsshoper.ui.theme.WhiteColor

import com.google.firebase.auth.FirebaseAuth
import okhttp3.Route

data class bottomnavigationItem(
    val name : String,
    val selectedIcon : ImageVector,
    val unselectedIcon : ImageVector
)

@Composable
fun navApp(firebaseAuth: FirebaseAuth) {

    val navController = rememberNavController()

    val items = listOf(
        bottomnavigationItem("Home", Icons.Filled.Home, Icons.Outlined.Home),
        bottomnavigationItem("fav", Icons.Filled.HeartBroken, Icons.Outlined.HeartBroken),
        bottomnavigationItem("cart", Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
        bottomnavigationItem("profile", Icons.Filled.Person2, Icons.Outlined.Person2)
    )
    var selecteditemIndex by remember { mutableStateOf(0) }
    val currentDestinationasState = navController.currentBackStackEntryAsState()
    val currentDestination = currentDestinationasState.value?.destination?.route
    val shouldShowBottomBar = remember { mutableStateOf(false) }


    LaunchedEffect(currentDestination) {
        shouldShowBottomBar.value = when(currentDestination){
            Routes.loginScreen::class.qualifiedName, Routes.singupScreen::class.qualifiedName -> false
            else -> true
        }

    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            if (shouldShowBottomBar.value){
                BottomNavigation(
                    items = items,
                    selectedItemIndex = selecteditemIndex,
                    onItemClick = { index ->
                        selecteditemIndex = index
                        when(selecteditemIndex){
                            0 -> navController.navigate(Routes.homeScreen)
                            1 -> navController.navigate(Routes.wishlistScreen)
                            2 -> navController.navigate(Routes.shopingCartScreen)
                            3 -> navController.navigate(Routes.profileScreen)
                        }
                    }
                )
            }
        },

    ){ innerPadding ->
        var startingScreen = if (firebaseAuth.currentUser == null){
            subNavigation.loginSingupScreen
        }else{
            subNavigation.mainhomeScreen
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = innerPadding.calculateBottomPadding())
        ) {
            NavHost(
                navController = navController,
                startDestination = startingScreen,
            ) {

                navigation<subNavigation.loginSingupScreen>(startDestination = Routes.loginScreen ){
                    composable<Routes.loginScreen> {
                        loginScreenui(navcontroller = navController)
                    }
                    composable<Routes.singupScreen> {
                        singUpScreenUi(navcontroller = navController)
                    }
                }
                navigation<subNavigation.mainhomeScreen>(startDestination = Routes.homeScreen){
                    composable<Routes.homeScreen> {
                        homeScreenUi(navcontroller = navController)
                    }
                    composable<Routes.profileScreen> {
                        profileScreenUi(
                            navController = navController,
                            firebaseAuth = firebaseAuth
                        )
                    }
                    composable<Routes.shopingCartScreen>{
                        shopingCartScreenUi(
                            navcontroller = navController
                        )

                    }

                }

                composable<Routes.productDetailedScreen> {
                    val data = it.toRoute<Routes.productDetailedScreen>()
                    productDetailedScreenUi(
                        productId = data.productId,
                        navcontroller = navController
                    )
                }
                composable<Routes.shipingScreenScreen> {
                    val data = it.toRoute<Routes.shipingScreenScreen>()
                    shipingScreenUi(
                        navController = navController,
                        productID = data.productID,
                        productQuintity = data.productQuintity,
                        productColorCode = data.productColorCode,
                        productColorName = data.productColorName,
                        productSize = data.productSize
                    )
                }
                composable<Routes.seeAllCategoryScreen> {
                    seeAllCategoryUi(
                        navController = navController
                    )
                }
                composable<Routes.seemoreProduct> {
                    seemoreProductUi(
                        navcontroller = navController
                    )

                }


            }


        }


    }

}