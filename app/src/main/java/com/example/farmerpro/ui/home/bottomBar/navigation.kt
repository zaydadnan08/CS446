package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.farmerpro.ui.home.markets.ItemsScreen
import com.example.farmerpro.ui.home.fridge.CommunityFridgeScreen
import com.example.farmerpro.ui.home.market.MarketScreen

@Composable
fun navigation(navController: NavHostController, mainNavController: NavController){
    NavHost(navController = navController, startDestination = Screens.Market.name){
        composable(route = Screens.Market.name) {
            MarketScreen()
        }
        composable(route = Screens.Farmer.name) {
            ItemsScreen()
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
