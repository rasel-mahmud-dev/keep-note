package com.example.keep.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.keep.components.NoteCard
import com.example.keep.components.NoteSearchBar
import com.example.keep.models.Note
import com.example.keep.store.NoteDatabaseHelper


@Composable
fun NoteHome(context: Context, navHostController: NavHostController) {

    val notes = remember { mutableStateListOf<Note>() }

    LaunchedEffect(Unit) {
        val dbHelper = NoteDatabaseHelper(context)
        val allNotes = dbHelper.getAllNotes()
        Log.d("nte", allNotes.toString())
        notes.addAll(allNotes)
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier
                .fillMaxSize()
                .padding(0.dp, 0.dp, 0.dp, 0.dp),
            contentPadding = PaddingValues(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),

        ) {

            item(span = { GridItemSpan(2) }) {
                Box(modifier = Modifier.padding(0.dp, 16.dp, 0.dp, 0.dp)) {
                    NoteSearchBar(
                        onSearchTextChange = {},
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 16.dp)
                    )
                }
            }

            items(notes) { note ->
                NoteCard(note = note, onClick = {
                    navHostController.navigate("edit/${note.id.toString()}")
                })
            }
        }

        FloatingActionButton(
            onClick = { navHostController.navigate("create") },
            modifier = Modifier
                .zIndex(1f)  // Make sure the FAB is on top of the grid
                .align(Alignment.BottomEnd)  // Positioned at the bottom-end
                .padding(16.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add Note")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewNoteHome() {
    NoteHome(
        context = LocalContext.current,
        navHostController = rememberNavController()
    )
}