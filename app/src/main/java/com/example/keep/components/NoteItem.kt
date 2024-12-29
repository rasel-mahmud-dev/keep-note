package com.example.keep.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.keep.models.Note
import com.example.keep.theme.AppColors

@Composable
fun NoteItem(note: Note) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .background(AppColors.Pink40)
                .padding(8.dp)
        ) {
            Text(
                text = note.title,
                fontWeight = FontWeight.Medium,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
            Text(
                text = note.content,
                fontSize = 14.sp
            )
        }
    }
}

