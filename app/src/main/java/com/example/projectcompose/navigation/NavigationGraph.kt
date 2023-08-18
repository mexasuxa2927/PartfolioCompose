package com.example.projectcompose.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.projectcompose.Utils.ActionListner
import com.example.projectcompose.ViewModel.SharedViewModel
import com.example.projectcompose.screens.ItemScreen
import com.example.projectcompose.screens.LoginScreen
import com.example.projectcompose.screens.MainScreen
import com.example.projectcompose.screens.SignUpScreen
import com.example.projectcompose.screens.SplashScreen

@Composable
fun NavigationGraph (navController: NavHostController,actionListner: ActionListner){
    var sharedViewModel:SharedViewModel = viewModel()

    NavHost(navController = navController, startDestination = NavigationScreen.SplashScreen.route){
        composable(NavigationScreen.ItemScreen.route+"/{data}"){
            ItemScreen(sharedViewModel)
        }
        composable(NavigationScreen.SplashScreen.route){

            SplashScreen()
        }
        composable(NavigationScreen.LoginScreen.route){
            LoginScreen(actionListner)
        }
        composable(NavigationScreen.SignUpScreen.route){
            SignUpScreen(actionListner)
        }
        composable(NavigationScreen.MainScreen.route){
            MainScreen(actionListner,sharedViewModel)
        }

    }
}