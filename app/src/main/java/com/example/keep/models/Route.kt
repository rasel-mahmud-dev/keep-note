package com.example.keep.models

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

data class Route(
    val path: String,
    val content: @Composable (NavBackStackEntry) -> Unit
)

