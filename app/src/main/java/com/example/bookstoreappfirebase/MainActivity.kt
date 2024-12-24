package com.example.bookstoreappfirebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.bookstoreappfirebase.ui.theme.add_book_screen.AddBookScreen
import com.example.bookstoreappfirebase.ui.theme.login.LoginScreen
import com.example.bookstoreappfirebase.ui.theme.login.navigation.AddBookScreenObject
import com.example.bookstoreappfirebase.ui.theme.login.navigation.LoginScreenObject
import com.example.bookstoreappfirebase.ui.theme.login.navigation.MainScreenDataObject
import com.example.bookstoreappfirebase.ui.theme.main_screen.MainScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(
                navController = navController,
                startDestination = LoginScreenObject){

                    composable<LoginScreenObject>{
                        LoginScreen{ navData ->
                            navController.navigate(navData)
                        }
                    }
                    composable<MainScreenDataObject>{ navEntry->
                        val navData = navEntry.toRoute<MainScreenDataObject>()
                        MainScreen(navData){
                            navController.navigate(AddBookScreenObject)
                        }
                    }
                    composable<AddBookScreenObject>{ navEntry->
                        AddBookScreen{
                            navController.popBackStack()
                        }
                    }
            }
        }
    }
}