package com.example.keep.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.keep.theme.AppColors

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteSearchBar(
    onSearchTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    val searchText = remember { mutableStateOf("") }

    Box(
        modifier = modifier
            .zIndex(1f)
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .background(AppColors.Transparent)
    ) {
        TextField(
            value = searchText.value,
            onValueChange = onSearchTextChange,
            placeholder = {
                Text(text = "Search your notes...", color = AppColors.Gray40)
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = AppColors.Gray40
                )
            },
            trailingIcon = {
                if (searchText.value.isNotBlank()) {
                    IconButton(onClick = { onSearchTextChange("") }) {
                        Icon(
                            imageVector = Icons.Default.Clear,
                            contentDescription = "Clear Search",
                            tint = AppColors.Gray40
                        )
                    }
                }
            },
            singleLine = true,
            shape = RoundedCornerShape(24.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = AppColors.MainBgLight,
                focusedIndicatorColor = Color.Transparent, // Remove the focused underline
                unfocusedIndicatorColor = Color.Transparent, // Remove the unfocused underline
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        )

    }
}
