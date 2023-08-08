package com.example.projectcompose.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectcompose.screens.mainpageScreens.HeartScreen
import com.example.projectcompose.screens.mainpageScreens.HomeScreen
import com.example.projectcompose.screens.mainpageScreens.ShoppingScreen

@Composable
fun BottomNavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen()
        }
        composable(BottomNavItem.Heart.screen_route) {
            HeartScreen()
        }
        composable(BottomNavItem.Shopping.screen_route) {
            ShoppingScreen()
        }
    }
}