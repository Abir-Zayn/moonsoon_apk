package com.example.moonsoon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.moonsoon.ui.Screen.WeatherScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // Add navigation graph here

    NavHost(
        navController, startDestination = Routes.Home_screen) {
        composable(Routes.Home_screen) {
                //Integrate WeatherScreen here
                WeatherScreen()
        }
    }
}