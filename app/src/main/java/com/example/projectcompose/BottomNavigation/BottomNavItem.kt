package com.example.projectcompose.BottomNavigation

import com.example.projectcompose.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String){

    object Home : BottomNavItem("Home", R.drawable.home_icon,"home")
    object Heart: BottomNavItem("Like",R.drawable.heart_icon,"heart")
    object Shopping: BottomNavItem("Shopping",R.drawable.shopping_icon2,"shopping")

}