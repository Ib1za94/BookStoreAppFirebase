package com.example.bookstoreappfirebase.ui.theme.main_screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import com.example.bookstoreappfirebase.data.Book
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor
import com.example.bookstoreappfirebase.ui.theme.login.navigation.MainScreenDataObject
import com.example.bookstoreappfirebase.ui.theme.main_screen.bottom_menu.BookListItemUi
import com.example.bookstoreappfirebase.ui.theme.main_screen.bottom_menu.BottomMenu
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

@Composable
fun MainScreen(
    navData: MainScreenDataObject,
    onAdminClick: () -> Unit
) {
    val drawState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()
    val bookListState = remember {
        mutableStateOf(emptyList<Book>())
    }

    LaunchedEffect(Unit) {
        val db = Firebase.firestore
        getAllBooks(db) { books ->
            bookListState.value = books
        }
    }

    ModalNavigationDrawer(
        drawerState = drawState,
        modifier = Modifier.fillMaxWidth(),
        drawerContent = {
            Column(modifier = Modifier.fillMaxWidth(0.7f)) {
                DrawerHeader(navData.email)
                DrawerBody() {
                    coroutineScope.launch {
                        drawState.close()
                    }
                    onAdminClick()
                }
            }
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = { BottomMenu() },
            containerColor = TextFieldColor
        ) { paddingValues ->
            LazyVerticalGrid(
                GridCells.Fixed(2),
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                items(bookListState.value) { book ->
                    BookListItemUi(
                        book,
                        onFavoriteClick = {}
                    )
                }
            }
        }
    }
}

private fun getAllBooks(
    db: FirebaseFirestore,
    onBooks: (List<Book>) -> Unit
) {
    db.collection("books")
        .get()
        .addOnSuccessListener { task ->
            onBooks(task.toObjects(Book::class.java))
        }
}