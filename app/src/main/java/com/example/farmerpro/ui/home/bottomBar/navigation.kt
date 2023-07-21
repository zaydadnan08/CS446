package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.farmerpro.ui.home.farmer.FarmerHomeScreen
import com.example.farmerpro.ui.home.farmer.InventoryItem.InventoryItemScreen
import com.example.farmerpro.ui.home.markets.ItemsScreen
import com.example.farmerpro.ui.home.fridge.CommunityFridgeScreen

@Composable
fun navigation(navController: NavHostController, mainNavController: NavController){
    NavHost(navController = navController, startDestination = Screens.Fridge.name){
        composable(route = Screens.Market.name) {
            ItemsScreen()
        }
        composable(route = Screens.Farmer.name) {
            val farmerNavController = rememberNavController()
            NavHost(navController = farmerNavController, startDestination = FarmerSubScreens.FarmerHome.name) {
                composable(route = FarmerSubScreens.FarmerHome.name) {
                    FarmerHomeScreen(navController = farmerNavController)
                }
                composable(
                    route = "${FarmerSubScreens.InventoryItem.name}/{name}/{quantity}/{unit}/{notes}",
                ) { backStackEntry ->
                    InventoryItemScreen(
                        farmerNavController,
                        backStackEntry.arguments?.getString("name"),
                        backStackEntry.arguments?.getString("quantity")?.toDoubleOrNull(),
                        backStackEntry.arguments?.getString("unit"),
                        backStackEntry.arguments?.getString("notes"),
                    )
                }
            }
        }
        composable(route = Screens.Fridge.name) {
            CommunityFridgeScreen(navController = navController)
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
