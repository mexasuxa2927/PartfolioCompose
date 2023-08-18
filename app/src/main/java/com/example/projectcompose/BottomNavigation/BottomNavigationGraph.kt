package com.example.projectcompose.BottomNavigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.SharedViewModel
import com.example.projectcompose.screens.mainpageScreens.HeartScreen
import com.example.projectcompose.screens.mainpageScreens.HomeScreen
import com.example.projectcompose.screens.mainpageScreens.ShoppingScreen

@Composable
fun BottomNavigationGraph(navController: NavHostController,actionListner: ActionListner,sharedViewModel: SharedViewModel) {
    NavHost(navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(actionListner, sharedViewModel =sharedViewModel )
        }
        composable(BottomNavItem.Heart.screen_route) {
            HeartScreen()
        }
        composable(BottomNavItem.Shopping.screen_route) {
            ShoppingScreen()
        }
    }
}