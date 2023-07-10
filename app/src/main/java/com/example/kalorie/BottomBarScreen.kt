package com.example.kalorie

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val route : String,
    val title : String,
    val icon: ImageVector
)
{
    object Home : BottomBarScreen(
        route = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Archive : BottomBarScreen(
        route = "archive",
        title = "Archive",
        icon = Icons.Default.List
    )
    object User : BottomBarScreen(
        route = "user",
        title = "User",
        icon = Icons.Default.Person
    )
}