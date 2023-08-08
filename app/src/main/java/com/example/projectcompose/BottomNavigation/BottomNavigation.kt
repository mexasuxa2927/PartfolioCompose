package com.example.projectcompose.BottomNavigation

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectcompose.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BottomNavigationMainScreen() {
    val navController = rememberNavController()


    Box(modifier = Modifier.fillMaxSize()){
        BottomNavigationGraph(navController = navController)
        Column( modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(horizontal = 10.dp)){

            Card(shape =
                RoundedCornerShape(50)
            ,
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)

            ) {
                BottomBar(navController = navController)
            }

            Spacer(modifier = Modifier.height(10.dp))
        }

    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomNavItem.Home,
        BottomNavItem.Heart,
        BottomNavItem.Shopping,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(modifier = Modifier.height(60.dp)) {

        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController,
                select  = screen.screen_route==currentDestination?.route
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomNavItem,
    currentDestination: NavDestination?,
    navController: NavHostController,
    select:Boolean
) {
    BottomNavigationItem(
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.screen_route
        } == true,
        icon = {
            Icon(
                modifier = Modifier
                    .height(24.dp)
                    .width(24.dp),
                painter = painterResource(id = screen.icon),
                contentDescription = "Navigation Icon",
                tint = if (select) colorResource(id = R.color.orange) else colorResource(id = R.color.white_gray)
            )
        },

        modifier = Modifier.background(Color.White)
        ,
        selectedContentColor = Color.Blue
        ,
        unselectedContentColor = Color.White ,
        onClick = {
            navController.navigate(screen.screen_route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}

@Preview
@Composable
fun checkUI(){
    BottomNavigationMainScreen()
}