package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.farmerpro.ui.home.farmer.FarmerScreen
import com.example.farmerpro.ui.home.fridge.CommunityFridgeScreen
import com.example.farmerpro.ui.home.profile.ProfileImageScreen

@Composable
fun navigation(navController: NavHostController, mainNavController: NavController){
    NavHost(navController = navController, startDestination = Screens.Market.name){
        composable(route = Screens.Market.name) {
            ProfileImageScreen()
        }
        composable(route = Screens.Farmer.name) {
            FarmerScreen()
        }
        composable(route = Screens.Fridge.name) {
            CommunityFridgeScreen(navController = mainNavController)
        }
    }
}

enum class Screens {
    Farmer,
    Market,
    Fridge,
}
