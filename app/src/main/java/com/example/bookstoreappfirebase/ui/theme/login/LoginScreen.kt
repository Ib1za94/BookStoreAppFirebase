package com.example.bookstoreappfirebase.ui.theme.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoreappfirebase.R
import com.example.bookstoreappfirebase.ui.theme.UI.BackgroundBox
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor
import com.example.bookstoreappfirebase.ui.theme.login.navigation.MainScreenDataObject
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(
    onNavigateToMainScreen: (MainScreenDataObject) -> Unit
) {

    val auth = remember {
        Firebase.auth
    }

    val errorState = remember {
        mutableStateOf("")
    }

    val emailState = remember {
        mutableStateOf("ib1za.coding@gmail.com")
    }

    val passwordState = remember {
        mutableStateOf("123456789")
    }

    Image(painter = painterResource(id = R.drawable.background),
        contentDescription = "background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop)

    Box(modifier = Modifier.fillMaxSize()
        .background(BackgroundBox))

    Column(modifier = Modifier.fillMaxSize()
        .padding(
            start = 50.dp,
            end = 50.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(painter = painterResource(id = R.drawable.book_logo),
            contentDescription = "logo",
            modifier = Modifier.height(110.dp))

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "ReadHub",
            color = TextFieldColor,
            fontFamily = PoppinsSemiBold,
            fontSize = 35.sp
            )

        Spacer(modifier = Modifier.height(15.dp))

        RoundedTextField(text = emailState.value,
            label = "Email:", onValueChange = {emailState.value = it},
            placeholder = "Enter your email")

        Spacer(modifier = Modifier.height(10.dp))

        RoundedTextField(text = passwordState.value,
            label = "Password:", onValueChange = {passwordState.value = it},
            placeholder = "Enter your password")

        Spacer(modifier = Modifier.height(10.dp))

        if(errorState.value.isNotEmpty()){
        Text(text = errorState.value,
            color = Color.Red,
            textAlign = TextAlign.Center
        )}

        LoginButton(text = "Sign in", onClick = {
            signIn(
                auth,
                emailState.value,
                passwordState.value,
                onSignInSuccess = { navData ->
                    onNavigateToMainScreen(navData)
                },
                onSignInFailure = { error ->
                    errorState.value = error
                })
        })

        LoginButton(text = "Sign up", onClick = {
            signUp(
                auth,
                emailState.value,
                passwordState.value,
                onSignUpSuccess = { navData ->
                    onNavigateToMainScreen(navData)
                },
                onSignUpFailure = { error ->
                    errorState.value = error
                })
        })
    }
}

fun signUp(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignUpSuccess: (MainScreenDataObject) -> Unit,
    onSignUpFailure: (String) -> Unit
) {
    if(email.isBlank() || password.isBlank())
        return onSignUpFailure("Email and password cannot be empty")

    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignUpSuccess(
                    MainScreenDataObject(
                        task.result.user?.uid!!,
                        task.result.user?.email!!
                    )
                )
            }
        }
        .addOnFailureListener {
            onSignUpFailure(it.message ?: "Sign up failed")
        }
}

fun signIn(
    auth: FirebaseAuth,
    email: String,
    password: String,
    onSignInSuccess: (MainScreenDataObject) -> Unit,
    onSignInFailure: (String) -> Unit
) {
    if(email.isBlank() || password.isBlank())
        return onSignInFailure("Email and password cannot be empty")

    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onSignInSuccess(
                    MainScreenDataObject(
                        task.result.user?.uid!!,
                        task.result.user?.email!!
                    )
                )
            }
        }
        .addOnFailureListener {
            onSignInFailure(it.message ?: "Sign in failed")
        }
}