package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.farmerpro.ui.landing.StartScreen
import com.example.farmerpro.ui.landing.UserSelectScreen
import com.example.farmerpro.ui.landing.UserSignupScreen


@Composable
fun navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Market.name){
        composable(route = Screens.Market.name) {
            StartScreen(navController = navController)
        }
        composable(route = Screens.Farmer.name) {
            UserSelectScreen(navController = navController)
        }
        composable(route = Screens.Fridge.name) {
            UserSignupScreen(navController = navController)
        }
    }
}

enum class Screens {
    Farmer,
    Market,
    Fridge,
}
