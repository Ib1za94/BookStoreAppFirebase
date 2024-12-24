package com.example.bookstoreappfirebase.ui.theme.login.navigation

import kotlinx.serialization.Serializable

@Serializable
data class MainScreenDataObject(
    val uid: String = "",
    val email: String = ""
)
