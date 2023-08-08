package com.example.projectcompose.navigation

sealed class NavigationScreen(val route:String){

    object LoginScreen:NavigationScreen("login_screen")
    object MainScreen:NavigationScreen("main_screen")
    object ItemScreen:NavigationScreen("item_screen")
    object SignUpScreen:NavigationScreen("signup_screen")
    object SplashScreen:NavigationScreen("splash_screen")
    object SettingsScreen:NavigationScreen("settings_screen")
    object ProfileScreen:NavigationScreen("profile_screen")
    object InfoScreen:NavigationScreen("info_screen")


}
