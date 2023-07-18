package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.farmerpro.ui.home.farmer.FarmerHomeScreen
import com.example.farmerpro.ui.home.farmer.InventoryItemScreen
import com.example.farmerpro.ui.home.markets.ItemsScreen
import com.example.farmerpro.ui.home.fridge.CommunityFridgeScreen
import com.example.farmerpro.ui.home.markets.ItemsScreen

@Composable
fun navigation(navController: NavHostController, mainNavController: NavController){
    NavHost(navController = navController, startDestination = Screens.Market.name){
        composable(route = Screens.Market.name) {
            ItemsScreen()
        }
        composable(route = Screens.Farmer.name) {
            val farmerNavController = rememberNavController()
            NavHost(navController = farmerNavController, startDestination = FarmerSubScreens.FarmerHome.name) {
                composable(route = FarmerSubScreens.FarmerHome.name) {
                    FarmerHomeScreen(navController = farmerNavController)
                }
                composable(route = FarmerSubScreens.InventoryItem.name) {
                    InventoryItemScreen()
                }
            }
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

enum class FarmerSubScreens {
    FarmerHome,
    InventoryItem,
}
