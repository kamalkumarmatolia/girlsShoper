package com.example.girlsshoper.presentation.components.card

import android.R.attr.icon
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.StarHalf
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material.icons.filled.StarHalf
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showBackground = true)

@Composable
fun ratingCard(rating: Float = 0f) {
    Row(modifier = Modifier.fillMaxWidth()) {
        for (i in 1..5) {
            val iconStar = when {
                i <= rating.toFloat() -> Icons.Default.Star          // full star
                i - rating in 0.1 ..0.9 -> Icons.AutoMirrored.Filled.StarHalf // half star
                else -> Icons.Default.StarBorder           // empty star

            }
            Icon(
                imageVector = iconStar,
                contentDescription = null,
                tint = Color.Yellow,
                modifier = Modifier.size(27.dp)
            )
            if (i != 5) {
                Spacer(modifier = Modifier.width(5.dp)) // adjust width as needed
            }
        }
    }
}
