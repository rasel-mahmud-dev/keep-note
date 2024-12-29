package com.example.keep.routes

import CreateNewNote
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.keep.models.Route
import com.example.keep.screens.NoteHome


@Composable
fun Routes(context: Context) {
    val navController = rememberNavController()

    val routes = listOf(
        Route(path = "home", content = { NoteHome(context, navController) }),
        Route(path = "create", content = { CreateNewNote(context, navController) }),
        Route(path = "appDetails/{appName}", content = { backStackEntry ->
            val appName = backStackEntry.arguments?.getString("appName")
//            AppDetailsScreen(appName ?: "Unknown")
        })
    )

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        routes.forEach { route ->
            composable(route.path) {
                route.content(it)
            }
        }
    }
}
