package com.example.girlsshoper.presentation.components.texture

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.girlsshoper.ui.theme.MainColor

@Composable
fun outlineTextFieldComponent(
    value : String,
    onValueChange : (String) -> Unit,
    label : String = "",
    modifier: Modifier = Modifier.fillMaxWidth(),
) {

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier,
        singleLine = true,
        shape = RoundedCornerShape(13.dp),
        colors = OutlinedTextFieldDefaults.colors(unfocusedBorderColor = MainColor,focusedBorderColor = MainColor)
    )

}
