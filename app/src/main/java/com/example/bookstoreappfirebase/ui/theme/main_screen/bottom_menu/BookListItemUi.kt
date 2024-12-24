package com.example.bookstoreappfirebase.ui.theme.main_screen.bottom_menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookstoreappfirebase.data.Book
import com.example.bookstoreappfirebase.ui.theme.UI.AdminPanel
import com.example.bookstoreappfirebase.ui.theme.UI.ButtonColor
import com.example.bookstoreappfirebase.ui.theme.UI.CollectionsColor
import com.example.bookstoreappfirebase.ui.theme.UI.DrawerHeaderColor
import com.example.bookstoreappfirebase.ui.theme.UI.Poppins
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold

@Composable
fun BookListItemUi(book: Book, onFavoriteClick: (Book) -> Unit) {

    val isFavorite = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        AsyncImage(
            model = book.imageUrl,
            contentDescription = "book image",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = book.title,
            color = DrawerHeaderColor,
            fontFamily = PoppinsSemiBold,
            fontSize = 20.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = book.description,
            color = AdminPanel,
            fontFamily = Poppins,
            fontSize = 16.sp,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Price: ${book.price}",
                color = ButtonColor,
                fontFamily = PoppinsSemiBold,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.weight(1f))

            IconButton(
                onClick = {
                    isFavorite.value = !isFavorite.value
                    onFavoriteClick(book)
                },
                modifier = Modifier.offset(y = (-2).dp)
            )
            {
                Icon(
                    imageVector = if (isFavorite.value) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "favorite icon",
                    tint = if (isFavorite.value) CollectionsColor else ButtonColor,
                    modifier = Modifier.size(30.dp)
                )
            }
        }


    }
}