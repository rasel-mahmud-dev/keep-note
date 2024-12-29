package com.example.keep.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.keep.models.Note
import com.example.keep.theme.AppColors

@Composable
fun NoteCard(note: Note) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .height(150.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(AppColors.PurpleGrey40) // Background color of the card
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.bodyLarge.copy(color = Color.White),
                maxLines = 1,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodySmall.copy(color = Color.White),
                maxLines = 4,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
