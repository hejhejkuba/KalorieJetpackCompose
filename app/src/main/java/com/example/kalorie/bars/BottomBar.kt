package com.example.kalorie.bars

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.kalorie.BottomBarScreen
import com.example.kalorie.R

@Composable
fun BottomBar(navController: NavController)
{
    Row(modifier = Modifier.fillMaxWidth().background(color = MaterialTheme.colorScheme.primary).padding(10.dp)
    )
    {
        Icon(imageVector = Icons.Default.Home, contentDescription = "home", Modifier.weight(1f).size(40.dp).clickable{navController.navigate(route = BottomBarScreen.Home.route) })
        Icon(imageVector = Icons.Default.List, contentDescription = "list", Modifier.weight(1f).size(40.dp).clickable{ navController.navigate(route = BottomBarScreen.Archive.route) })
        Icon(imageVector = Icons.Default.Person, contentDescription = "user", Modifier.weight(1f).size(40.dp).clickable{ navController.navigate(route = BottomBarScreen.User.route) })
    }

}
