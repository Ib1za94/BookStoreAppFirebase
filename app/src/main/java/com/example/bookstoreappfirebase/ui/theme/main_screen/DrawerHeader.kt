package com.example.bookstoreappfirebase.ui.theme.main_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoreappfirebase.R
import com.example.bookstoreappfirebase.ui.theme.UI.CollectionsBorderColor
import com.example.bookstoreappfirebase.ui.theme.UI.CollectionsColor
import com.example.bookstoreappfirebase.ui.theme.UI.DrawerHeaderColor
import com.example.bookstoreappfirebase.ui.theme.UI.MontserratSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.OswaldSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor

@Composable
fun DrawerHeader(email: String){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(170.dp)
            .background(DrawerHeaderColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(modifier = Modifier.size(90.dp),
            painter = painterResource(id = R.drawable.book_logo),
            contentDescription = "logo")

        Spacer(modifier = Modifier.height(10.dp))

        Text(text = "ReadHub",
            color = TextFieldColor,
            fontFamily = PoppinsSemiBold,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(7.dp))

        Text(email,
            color = CollectionsBorderColor,
            fontFamily = MontserratSemiBold,
            fontSize = 13.sp,
        )
    }
}