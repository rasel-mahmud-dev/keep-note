package com.example.keep.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.keep.components.CreateNewNote.CreateNoteBottomBar
import com.example.keep.components.CreateNewNote.Header
import com.example.keep.components.EditableNote
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun CreateNewNote(context: Context, navHostController: NavHostController) {


    var isCreating by remember { mutableStateOf(false) }
    var title by remember { mutableStateOf(TextFieldValue("")) }
    var content by remember { mutableStateOf(TextFieldValue("")) }

    fun createNote() {
        if (title.text.isNotBlank() || content.text.isNotBlank()) {
            val currentDate =
                SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
//            notes.add(Note(title.text, content.text, currentDate))
            title = TextFieldValue("")
            content = TextFieldValue("")
            isCreating = false
        }
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF0E0E0E))
    ) {
        Header(navHostController)

        EditableNote(
            title = title,
            content = content,
            onTitleChange = { title = it },
            onContextChange = { content = it },
            modifier = Modifier.weight(1f)
        )

        CreateNoteBottomBar(navHostController)
    }


}