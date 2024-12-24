package com.example.bookstoreappfirebase.ui.theme.add_book_screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.example.bookstoreappfirebase.R
import com.example.bookstoreappfirebase.data.Book
import com.example.bookstoreappfirebase.ui.theme.UI.AddBookBackground
import com.example.bookstoreappfirebase.ui.theme.UI.PoppinsSemiBold
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor
import com.example.bookstoreappfirebase.ui.theme.login.LoginButton
import com.example.bookstoreappfirebase.ui.theme.login.RoundedTextField
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.ktx.storage

@Preview(showBackground = true)
@Composable
fun AddBookScreen(
    onSaved: () -> Unit = {}
) {

    var selectedCategory = "Category:"

    val title = remember {
        mutableStateOf("")
    }

    val description = remember {
        mutableStateOf("")
    }

    val price = remember {
        mutableStateOf("")
    }

    val selectedImageUri = remember {
        mutableStateOf<Uri?>(null)
    }

    val firebase = remember {
        Firebase.firestore
    }

    val storage = remember {
        Firebase.storage
    }

    val imageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        selectedImageUri.value = uri
    }

    Image(painter = rememberAsyncImagePainter(
        model = selectedImageUri.value),
        contentDescription = "background image",
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop,
        alpha = 0.7f)

    Box(modifier = Modifier.fillMaxSize()
        .background(AddBookBackground))

    Column(modifier = Modifier.fillMaxSize()
        .padding(
            start = 50.dp,
            end = 50.dp
        ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Image(painter = painterResource(id = R.drawable.book_logo),
            contentDescription = "logo",
            modifier = Modifier.height(90.dp))

        Spacer(modifier = Modifier.height(15.dp))

        Text(text = "Add new book",
            color = TextFieldColor,
            fontFamily = PoppinsSemiBold,
            fontSize = 25.sp
        )

        Spacer(modifier = Modifier.height(15.dp))

        RoundedCornerDropDownMenu { selectedItem ->
            selectedCategory = selectedItem
        }

        Spacer(modifier = Modifier.height(15.dp))

        RoundedTextField(text = title.value,
            label = "Title:", onValueChange = {title.value = it},
            placeholder = "Enter the book title")

        Spacer(modifier = Modifier.height(10.dp))

        RoundedTextField(text = description.value,
            label = "Description:", onValueChange = {description.value = it},
            placeholder = "Enter the book description",
            singleLine = false,
            maxLines = 5)

        Spacer(modifier = Modifier.height(10.dp))

        RoundedTextField(text = price.value,
            label = "Price:", onValueChange = {price.value = it},
            placeholder = "Enter the book price")

        Spacer(modifier = Modifier.height(10.dp))

        LoginButton(text = "Select image", onClick = {
            imageLauncher.launch("image/*")
        })

        LoginButton(text = "Save", onClick = {
            saveBookImage(
                selectedImageUri.value!!,
                storage,
                firebase,
                Book(
                    title = title.value,
                    description = description.value,
                    price = price.value,
                    category = selectedCategory
                ),
                onSaved = {
                    onSaved()
                },
                onError = {

                })
        })
    }
}

private fun saveBookImage(
    uri: Uri,
    storage: FirebaseStorage,
    firestore: FirebaseFirestore,
    book: Book,
    onSaved: () -> Unit,
    onError: (Exception) -> Unit
) {
    val timeStamp = System.currentTimeMillis()
    val storageRef = storage.reference
        .child("books_images")
        .child("image_$timeStamp.jpg")
    val uploadTask = storageRef.putFile(uri)

    uploadTask.addOnSuccessListener {
        storageRef.downloadUrl.addOnSuccessListener { url ->
            saveBookToFireStore(
                firestore,
                url.toString(),
                book,
                onSaved = {
                    onSaved()
                },
                onError = {
                    onError(Exception("Failed to save book"))
                })
        }
    }.addOnFailureListener {}
}

private fun saveBookToFireStore(
    firestore: FirebaseFirestore,
    url: String,
    book: Book,
    onSaved: () -> Unit,
    onError: (Exception) -> Unit
){
    val db = firestore.collection("books")
    val key = db.document().id
    db.document(key).set(book.copy(key = key, imageUrl = url))
        .addOnSuccessListener{
            onSaved()
        }.addOnFailureListener{
            onError(it)
        }
}