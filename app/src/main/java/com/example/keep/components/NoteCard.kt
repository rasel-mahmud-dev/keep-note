package com.example.keep.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.keep.models.Note
import com.example.keep.theme.AppColors

@Composable
fun NoteCard(note: Note) {

    val lineCount = note.content.split("\n").size
    val cardHeight = when {
        lineCount <= 10 -> 100.dp
        lineCount <= 30 -> 200.dp
        else -> 300.dp
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)
            .border(1.dp, color = AppColors.Gray40, shape = RoundedCornerShape(12.dp)),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(
            modifier = Modifier
                .background(AppColors.MainBg)
                .fillMaxSize()
                .padding(16.dp)
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
                maxLines = if (lineCount > 10) lineCount else 10,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
