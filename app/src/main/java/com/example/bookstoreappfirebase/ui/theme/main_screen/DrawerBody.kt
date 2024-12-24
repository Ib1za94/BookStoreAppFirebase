package com.example.bookstoreappfirebase.ui.theme.main_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoreappfirebase.R
import com.example.bookstoreappfirebase.ui.theme.UI.AdminPanel
import com.example.bookstoreappfirebase.ui.theme.UI.BorderColor
import com.example.bookstoreappfirebase.ui.theme.UI.CollectionsBorderColor
import com.example.bookstoreappfirebase.ui.theme.UI.CollectionsColor
import com.example.bookstoreappfirebase.ui.theme.UI.DrawerHeaderColor
import com.example.bookstoreappfirebase.ui.theme.UI.MontserratSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor
import com.example.bookstoreappfirebase.ui.theme.UI.UnderBox
import com.example.bookstoreappfirebase.ui.theme.UI.labelColor
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore

@Preview(showBackground = true)
@Composable
fun DrawerBody(
    onAdminClick: () -> Unit = {}
) {
    val categoriesList = listOf(
    "Favourites",
    "Drama",
    "Fantasy",
    "Bestsellers"
)

    val isAdminState = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        isAdmin { isAdmin ->
            isAdminState.value = isAdmin
        }
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(UnderBox)
    ){
        Box(modifier = Modifier
            .fillMaxSize()
            .background(labelColor)
        ){
            Image(
                painter = painterResource(R.drawable.background),
                contentDescription = "background picture",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop,
                alpha = 0.2f
            )

            Column(modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally) {

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5.dp)
                    .background(CollectionsBorderColor))

                Spacer(modifier = Modifier.height(14.dp))

                Text(text = "Collections",
                    fontFamily = PoppinsSemiBold,
                    fontSize = 22.sp,
                    color = CollectionsColor
                )

                Spacer(modifier = Modifier.height(10.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(1.5.dp)
                    .background(CollectionsBorderColor))

                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(categoriesList) { item ->
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .clickable {  }
                        ) {
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(text = item,
                                fontFamily = MontserratSemiBold,
                                fontSize = 18.sp,
                                color = TextFieldColor,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth())

                            Spacer(modifier = Modifier.height(12.dp))
                            Box(modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(BorderColor))
                        }
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))

                if(isAdminState.value) Button(
                    onClick = {
                        onAdminClick()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AdminPanel
                    )
                ) {
                    Text(text = "Admin panel",
                        fontFamily = MontserratSemiBold,
                        fontSize = 18.sp,
                        color = TextFieldColor
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .background(DrawerHeaderColor)
                    .align(Alignment.BottomCenter)
            ) {
                Text(text = "author: Oleksandr Byshovets\nmail: kolibelrazuma@gmail.com",
                    fontFamily = FontFamily.SansSerif,
                    fontSize = 14.sp,
                    color = TextFieldColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .wrapContentWidth())
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.5.dp)
                        .background(CollectionsBorderColor)
                        .align(Alignment.TopCenter)
                )
            }
        }
    }


}

fun isAdmin(onAdmin: (Boolean) -> Unit) {
    val uid = Firebase.auth.currentUser!!.uid
    Firebase.firestore.collection("admin")
        .document(uid).get().addOnSuccessListener {
            Log.d("MyLog", "isAdmin: ${it.get("isAdmin")}")
            onAdmin(it.get("isAdmin") as Boolean)
        }
}