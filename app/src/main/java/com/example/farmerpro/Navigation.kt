package com.example.farmerpro

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.farmerpro.types.UserType
import com.example.farmerpro.ui.home.HomeScreen
import com.example.farmerpro.ui.landing.StartScreen
import com.example.farmerpro.ui.landing.UserSelectScreen
import com.example.farmerpro.ui.landing.UserSigninScreen
import com.example.farmerpro.ui.landing.UserSignupScreen

@Composable
fun Navigation(
    isLoggedIn: Boolean
){
    val navController = rememberNavController()
    var start = ""
    if (isLoggedIn) {
        start = Screens.Home.name
    } else {
        start = Screens.Start.name
    }

    NavHost(navController = navController, startDestination = start){
        composable(route = Screens.Start.name) {
            StartScreen(navController = navController)
        }
        composable(route = Screens.UserSelect.name) {
            UserSelectScreen(navController = navController)
        }
        composable(route = Screens.UserSignupCustomer.name) {
            UserSignupScreen(navController = navController, userType = UserType.CUSTOMER)
        }
        composable(route = Screens.UserSignupFarmer.name) {
            UserSignupScreen(navController = navController, userType = UserType.FARMER)
        }
        composable(route = Screens.UserSignupAdmin.name) {
            UserSignupScreen(navController = navController, userType = UserType.ADMIN)
        }
        composable(route = Screens.UserSignin.name) {
            UserSigninScreen(navController = navController)
        }
        composable(route = Screens.Home.name) {
            HomeScreen(mainNavController = navController)
        }
    }
}

enum class Screens {
    Home,
    Start,
    UserSignupCustomer,
    UserSignupFarmer,
    UserSignupAdmin,
    UserSignin,
    UserSelect,;
}


































