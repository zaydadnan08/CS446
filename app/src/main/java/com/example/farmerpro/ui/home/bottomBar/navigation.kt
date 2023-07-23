package com.example.farmerpro.ui.home.bottomBar

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.farmerpro.ui.home.farmer.FarmerHomeScreen
import com.example.farmerpro.ui.home.farmer.InventoryItem.InventoryItemScreen
import com.example.farmerpro.ui.home.farmer.farmViewModel
import com.example.farmerpro.ui.home.markets.ItemsScreen
import com.example.farmerpro.ui.home.fridge.CommunityFridgeScreen

@Composable
fun navigation(navController: NavHostController, mainNavController: NavController){
    NavHost(navController = navController, startDestination = Screens.Fridge.name){
        composable(route = Screens.Market.name) {
            ItemsScreen(navController = mainNavController)
        }
        composable(route = Screens.Farmer.name) {
            val farmerNavController = rememberNavController()
            NavHost(navController = farmerNavController, startDestination = FarmerSubScreens.FarmerHome.name) {
                composable(route = FarmerSubScreens.FarmerHome.name) {
                    FarmerHomeScreen(navController = farmerNavController, mainNavController = mainNavController)
                }
                composable(
                    route = "${FarmerSubScreens.InventoryItem.name}?name={name}&quantity={quantity}&unit={unit}&notes={notes}",
                    arguments = listOf(
                        navArgument("name") { defaultValue = "" },
                        navArgument("quantity") { defaultValue = "0" },
                        navArgument("unit") { defaultValue = "lbs" },
                        navArgument("notes") { defaultValue = "" })
                ) { backStackEntry ->
                    InventoryItemScreen(
                        navController = farmerNavController,
                        name = backStackEntry.arguments?.getString("name").toString(),
                        quantity = backStackEntry.arguments?.getString("quantity").toString().toDouble(),
                        unit = backStackEntry.arguments?.getString("unit").toString(),
                        notes = backStackEntry.arguments?.getString("notes").toString(),
                    )
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
