package com.example.keep.components.CreateNewNote

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.keep.theme.AppColors

@Composable
fun Header(navController: NavHostController) {
    Column(modifier = Modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                modifier = Modifier.size(35.dp),
                onClick = {
                    navController.navigate("home")
                }
            ) {
                Icon(
                    modifier = Modifier.padding(8.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = Color.White
                )
            }


            Row(
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                var iconColor by remember { mutableStateOf(Color.White) }
                IconButton(
                    modifier = Modifier.size(35.dp),
                    onClick = {
                        iconColor = Color.Red
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Default.FavoriteBorder,
                        contentDescription = "Love",
                        tint = iconColor
                    )
                }
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    },
                    modifier = Modifier.size(35.dp)
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Love",
                        tint = Color.White
                    )
                }
                IconButton(
                    modifier = Modifier.size(35.dp),
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(8.dp),
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Love",
                        tint = Color.White
                    )
                }
            }
        }
    }
}