package com.example.bookstoreappfirebase.ui.theme.login

import android.graphics.fonts.FontFamily
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.bookstoreappfirebase.ui.theme.UI.Montserrat
import com.example.bookstoreappfirebase.ui.theme.UI.MontserratSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.Poppins
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor
import com.example.bookstoreappfirebase.ui.theme.UI.labelColor

@Composable
fun RoundedTextField(
    singleLine: Boolean = true,
    maxLines: Int = 1,
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    placeholder: String
) {
    TextField(value = text, onValueChange = { onValueChange(it) },
        shape = RoundedCornerShape(25.dp),
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = TextFieldColor,
            focusedContainerColor = TextFieldColor,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ), modifier = Modifier.fillMaxWidth().border(1.dp, Color.Black, RoundedCornerShape(25.dp)),
        label = {
            Text(text = label, color = labelColor, fontFamily = Montserrat)
        },
        placeholder = {
            Text(text = placeholder, color = labelColor)
        },
        singleLine = singleLine,
        maxLines = maxLines
    )
}