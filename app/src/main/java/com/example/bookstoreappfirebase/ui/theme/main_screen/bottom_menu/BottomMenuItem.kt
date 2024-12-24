package com.example.bookstoreappfirebase.ui.theme.main_screen.bottom_menu

import com.example.bookstoreappfirebase.R

sealed class BottomMenuItem(
    val route: String,
    val title: String,
    val iconId: Int
) {
    object Home: BottomMenuItem(
        route = "",
        title = "Home",
        iconId = R.drawable.ic_home
    )
    object Favorites: BottomMenuItem(
        route = "",
        title = "Favorites",
        iconId = R.drawable.ic_fav
    )
    object Settings: BottomMenuItem(
        route = "",
        title = "Settings",
        iconId = R.drawable.ic_settings
    )
}