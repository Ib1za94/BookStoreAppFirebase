package com.example.bookstoreappfirebase.ui.theme.login

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.bookstoreappfirebase.ui.theme.UI.ButtonColor
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor

@Composable
fun LoginButton(
    text: String,
    onClick: () -> Unit) {
    Button(onClick = {
    onClick()
    }, modifier = Modifier
        .fillMaxWidth(0.6f),
        colors = ButtonDefaults.buttonColors(
        containerColor = ButtonColor, contentColor = TextFieldColor
        ),
    ){
        Text(text = text, style = TextStyle(fontSize = 17.sp, fontFamily = PoppinsSemiBold))
    }
}