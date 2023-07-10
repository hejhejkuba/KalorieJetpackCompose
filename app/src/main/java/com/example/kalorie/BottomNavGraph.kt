package com.example.kalorie

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.kalorie.screens.ArchiveScreen
import com.example.kalorie.screens.HomeScreen
import com.example.kalorie.screens.UserProfile

@Composable
fun BottomNavGraph(navController: NavHostController, state: CaloriesState, onEvent: (CaloriesEvent) -> Unit)
{
    NavHost(
        navController = navController ,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route){
            HomeScreen(navController, state, onEvent)
        }
        composable(route = BottomBarScreen.User.route){
            UserProfile(navController, state, onEvent)
        }
        composable(route = BottomBarScreen.Archive.route){
            ArchiveScreen(navController, state, onEvent)
        }
    }
}