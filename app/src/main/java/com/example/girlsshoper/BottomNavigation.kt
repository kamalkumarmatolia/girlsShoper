package com.example.girlsshoper

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.girlsshoper.presentation.navigation.bottomnavigationItem
import com.example.girlsshoper.ui.theme.BlackColor
import com.example.girlsshoper.ui.theme.GrayColor
import com.example.girlsshoper.ui.theme.MainColor
import com.example.girlsshoper.ui.theme.WhiteColor

@Composable
fun BottomNavigation(
    items: List<bottomnavigationItem>,
    selectedItemIndex: Int,
    onItemClick: (Int) -> Unit
) {
    NavigationBar(
        modifier = Modifier.fillMaxWidth().height(51.dp),
        containerColor = GrayColor.copy(alpha = 0.1f),
        tonalElevation = 10.dp,
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = { onItemClick(index) },
                icon = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = if(selectedItemIndex == index) item.selectedIcon else item.unselectedIcon,
                            contentDescription = null,
                            modifier = Modifier.size(24.dp)
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                    }
                }, colors = NavigationBarItemColors(
                    selectedIconColor = WhiteColor,
                    selectedTextColor = WhiteColor,
                    unselectedIconColor = BlackColor,
                    unselectedTextColor = BlackColor,
                    selectedIndicatorColor = MainColor,
                    disabledIconColor = Color.Unspecified,
                    disabledTextColor = Color.Gray
                )
            )
        }
    }
}