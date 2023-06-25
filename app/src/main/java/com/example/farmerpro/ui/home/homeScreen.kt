package com.example.farmerpro.ui.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.farmerpro.ui.home.bottomBar.Screens
import com.example.farmerpro.ui.home.bottomBar.navigation
import com.example.farmerpro.R

@Composable
fun HomeScreen() {
    val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.White,
                    contentColor = Color.Black,
                    modifier = Modifier
                        .border(
                            BorderStroke(3.dp, Color.Black),
                            CircleShape
                        )
                ) {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination

                    Screens.values().forEach { screen ->
                        BottomNavigationItem(
                            selected = currentDestination?.hierarchy?.any {
                                it.route == screen.name
                            }.isTrue(),
                            label = { Text(text = screen.name) },
                            onClick = {
                                navController.navigate(screen.name) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            },
                            icon = {
                                val id = when (screen) {
                                    Screens.Market -> R.drawable.home
                                    Screens.Fridge -> R.drawable.notification
                                    Screens.Farmer -> R.drawable.edit
                                }
                                Icon(
                                    painter = painterResource(id = id),
                                    modifier = Modifier.size(24.dp),
                                    contentDescription = null
                                )
                            }
                        )
                    }
                }
            }
        ) { innerPadding ->
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colors.background
            ) {
                navigation(navController = navController)
            }
        }
}

fun Boolean?.isTrue() = this == true
