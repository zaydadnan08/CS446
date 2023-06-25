package com.example.farmerpro

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.farmerpro.ui.home.HomeScreen
import com.example.farmerpro.ui.landing.StartScreen
import com.example.farmerpro.ui.landing.UserSelectScreen
import com.example.farmerpro.ui.landing.UserSignupScreen

@Composable
fun Navigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.Start.name){
        composable(route = Screens.Start.name) {
            StartScreen(navController = navController)
        }
        composable(route = Screens.UserSelect.name) {
            UserSelectScreen(navController = navController)
        }
        composable(route = Screens.UserSignup.name) {
            UserSignupScreen(navController = navController)
        }
        composable(route = Screens.Home.name) {
            HomeScreen()
        }
    }
}

enum class Screens {
    Home,
    Start,
    UserSignup,
    UserSelect,
}


































