package com.example.keep.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.keep.components.NoteCard
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

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {


        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(notes) { item ->
                NoteCard(item)
            }
        }


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

