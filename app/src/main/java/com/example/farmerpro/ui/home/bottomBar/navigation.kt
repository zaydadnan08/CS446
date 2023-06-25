package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.farmerpro.ui.home.farmer.FarmScreen
import com.example.farmerpro.ui.home.fridge.CommunityFridgeScreen
import com.example.farmerpro.ui.home.market.MarketScreen
import com.example.farmerpro.ui.landing.StartScreen
import com.example.farmerpro.ui.landing.UserSelectScreen
import com.example.farmerpro.ui.landing.UserSignupScreen


@Composable
fun navigation(navController: NavHostController){
    NavHost(navController = navController, startDestination = Screens.Home.name){
        composable(route = Screens.Home.name) {
            MarketScreen(navController = navController)
        }
        composable(route = Screens.Farmer.name) {
            FarmScreen(navController = navController)
        }
        composable(route = Screens.Fridge.name) {
            CommunityFridgeScreen(navController = navController)
        }
    }
}

enum class Screens {
    Farmer,
    Home,
    Fridge,
}
