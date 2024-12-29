package com.example.keep.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.keep.models.Note


@Composable
fun NoteHome(context: Context, navHostController: NavHostController) {
    val notes = remember { mutableStateListOf<Note>() }

    // Handle creating and updating notes

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {


//        LazyColumn(modifier = Modifier.fillMaxSize()) {
//            items(notes.size) { index ->
//                EditableNoteItem(
//                    note = notes[index],
//                    onNoteUpdated = { updatedNote ->
//                        notes[index] = updatedNote
//                    }
//                )
//                Spacer(modifier = Modifier.height(8.dp))
//            }
//        }


        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            FloatingActionButton(
                onClick = { navHostController.navigate("create") },
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(16.dp)
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Note")
            }
        }
    }
}

