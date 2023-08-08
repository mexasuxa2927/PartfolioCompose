package com.example.projectcompose.NavigationDrawer

import com.example.projectcompose.R
import com.example.projectcompose.navigation.NavigationScreen

sealed class NavigationDrowerItem (val title:String,val icon:Int,val route:String)
{
    object Settings:NavigationDrowerItem("Settings", R.drawable.settings_icon,NavigationScreen.SettingsScreen.route)
    object Profile:NavigationDrowerItem("Settings", R.drawable.profile_icon,NavigationScreen.ProfileScreen.route)
    object Info:NavigationDrowerItem("Info",R.drawable.info_icon,NavigationScreen.InfoScreen.route)
}