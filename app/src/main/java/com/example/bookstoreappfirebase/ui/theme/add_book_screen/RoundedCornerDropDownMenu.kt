package com.example.bookstoreappfirebase.ui.theme.add_book_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookstoreappfirebase.ui.theme.UI.CategoryListColor
import com.example.bookstoreappfirebase.ui.theme.UI.Montserrat
import com.example.bookstoreappfirebase.ui.theme.UI.TextFieldColor
import com.example.bookstoreappfirebase.ui.theme.UI.labelColor

@Composable
fun RoundedCornerDropDownMenu(
    onOptionSelected: (String) -> Unit
) {
    val expanded = remember { mutableStateOf(false) }
    val selectedOption = remember { mutableStateOf("Category:") }
    val categoriesList = listOf(
        "Drama",
        "Fantasy",
        "Bestsellers"
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(25.dp))
            .clip(RoundedCornerShape(25.dp))
            .background(color = TextFieldColor)
            .clickable{ expanded.value = true }
            .padding(17.dp)
    )
    {
        Text(text = selectedOption.value, fontFamily = Montserrat, color = labelColor, fontSize = 16.sp)
        DropdownMenu(
            expanded = expanded.value,
            onDismissRequest = { expanded.value = false },
            modifier = Modifier.background(color = CategoryListColor)
        ) {
            categoriesList.forEach { option ->
                DropdownMenuItem(
                    text = { Text(
                        text = option,
                        fontFamily = Montserrat,
                        color = labelColor,
                        fontSize = 14.sp
                    ) },
                    onClick = {
                        selectedOption.value = option
                        expanded.value = false
                        onOptionSelected(option)
                })
            }
        }
    }
}