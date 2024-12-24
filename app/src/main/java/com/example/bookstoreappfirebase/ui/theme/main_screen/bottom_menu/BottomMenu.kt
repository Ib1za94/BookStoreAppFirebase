package com.example.bookstoreappfirebase.ui.theme.main_screen.bottom_menu

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.painterResource
import com.example.bookstoreappfirebase.ui.theme.UI.CollectionsColor
import com.example.bookstoreappfirebase.ui.theme.UI.DrawerHeaderColor
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor

@Composable
fun BottomMenu() {
    val items = listOf(
        BottomMenuItem.Home,
        BottomMenuItem.Favorites,
        BottomMenuItem.Settings
    )

    val selectedItem = remember { mutableStateOf("Home") }

    NavigationBar(
        containerColor = DrawerHeaderColor
    ) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selectedItem.value == item.title,
                onClick = {
                    selectedItem.value = item.title
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = null
                    )
                },
                label = { Text(text = item.title) },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = TextFieldColor,
                    unselectedIconColor = TextFieldColor,
                    selectedTextColor = CollectionsColor,
                    unselectedTextColor = TextFieldColor,
                    indicatorColor = CollectionsColor
                )
            )
        }
    }
}