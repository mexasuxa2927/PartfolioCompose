package com.example.projectcompose.NavigationDrawer

import com.example.projectcompose.R
import com.example.projectcompose.navigation.NavigationScreen

sealed class NavigationDrowerItem (val title:String,val icon:Int,val route:String)
{
    object Info:NavigationDrowerItem("Info",R.drawable.info_icon,NavigationScreen.InfoScreen.route)
    object Profile:NavigationDrowerItem("Logout", R.drawable.logout_9,"Logout")

}